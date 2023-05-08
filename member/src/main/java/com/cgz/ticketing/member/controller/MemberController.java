package com.cgz.ticketing.member.controller;

import com.cgz.ticketing.common.resp.CommonResp;
import com.cgz.ticketing.member.req.MemberLoginReq;
import com.cgz.ticketing.member.req.MemberRegisterReq;
import com.cgz.ticketing.member.req.MemberSendCodeReq;
import com.cgz.ticketing.member.resp.MemberLoginResp;
import com.cgz.ticketing.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Resource
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> hello(){
        return new CommonResp<>(memberService.count());
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Validated MemberRegisterReq req){
        return new CommonResp<>(memberService.register(req));
    }

    @PostMapping("/sendCode")
    public CommonResp<Long> sendCode(@Validated @RequestBody MemberSendCodeReq req){
        memberService.sendCode(req);
        return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Validated MemberLoginReq req){
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }
}
