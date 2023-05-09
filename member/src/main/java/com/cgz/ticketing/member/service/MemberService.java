package com.cgz.ticketing.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.cgz.ticketing.common.exception.AppException;
import com.cgz.ticketing.common.exception.AppExceptionEnum;
import com.cgz.ticketing.common.util.JwtUtil;
import com.cgz.ticketing.common.util.SnowUtil;
import com.cgz.ticketing.member.domain.Member;
import com.cgz.ticketing.member.domain.MemberExample;
import com.cgz.ticketing.member.mapper.MemberMapper;
import com.cgz.ticketing.member.req.MemberLoginReq;
import com.cgz.ticketing.member.req.MemberRegisterReq;
import com.cgz.ticketing.member.req.MemberSendCodeReq;
import com.cgz.ticketing.member.resp.MemberLoginResp;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return (int) memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);
        if(ObjectUtil.isNotNull(memberDB)){
            throw new AppException(AppExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req){
        String mobile = req.getMobile();
        Member memberDB = selectByMobile(mobile);

        //如果手机号不存在，则插入记录
        if(ObjectUtil.isNull(memberDB)){
            LOG.info("手机号不存在，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        }

        //生成验证码
        String code = RandomUtil.randomString(4);
        LOG.info("生成短信验证码： {}", code);
        //保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("保存短信记录表");
        //对接短信通道，发送短信
        LOG.info("对接短信通道");
    }

    public MemberLoginResp login(MemberLoginReq req){
        String mobile = req.getMobile();
        String code = req.getCode();
        Member memberDB = selectByMobile(mobile);

        //如果手机号不存在，则插入记录
        if(ObjectUtil.isNull(memberDB)){
            throw new AppException(AppExceptionEnum.MEMBER_MOBILE_NOT_EXIST);
        }

        //校验短信验证码
        if(!"8888".equals(code)){
            throw new AppException(AppExceptionEnum.MEMBER_MOBILE_CODE_ERROR);
        }

        MemberLoginResp memberLoginResp = BeanUtil.copyProperties(memberDB, MemberLoginResp.class);

        //生成JWT
        String token = JwtUtil.createToken(memberLoginResp.getId(), memberLoginResp.getMobile());
        memberLoginResp.setToken(token);

        return memberLoginResp;
    }

    private Member selectByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(list)){
            return null;
        }else {
            return list.get(0);
        }
    }
}
