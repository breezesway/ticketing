package com.cgz.ticketing.member.controller;

import com.cgz.ticketing.common.resp.CommonResp;
import com.cgz.ticketing.member.req.MemberRegisterReq;
import com.cgz.ticketing.member.service.MemberService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
