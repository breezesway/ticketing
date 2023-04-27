package com.cgz.ticketing.member.service;

import cn.hutool.core.collection.CollUtil;
import com.cgz.ticketing.common.exception.AppException;
import com.cgz.ticketing.common.exception.AppExceptionEnum;
import com.cgz.ticketing.member.domain.Member;
import com.cgz.ticketing.member.domain.MemberExample;
import com.cgz.ticketing.member.mapper.MemberMapper;
import com.cgz.ticketing.member.req.MemberRegisterReq;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    public int count(){
        return (int) memberMapper.countByExample(null);
    }

    public long register(MemberRegisterReq req){
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list = memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(list)){
            throw new AppException(AppExceptionEnum.MEMBER_MOBILE_EXIST);
        }
        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
