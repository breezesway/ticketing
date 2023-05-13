package com.cgz.ticketing.member.controller;

import com.cgz.ticketing.common.context.LoginMemberContext;
import com.cgz.ticketing.common.resp.CommonResp;
import com.cgz.ticketing.member.req.PassengerQueryReq;
import com.cgz.ticketing.member.req.PassengerSaveReq;
import com.cgz.ticketing.member.resp.PassengerQueryResp;
import com.cgz.ticketing.member.service.PassengerService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Validated @RequestBody PassengerSaveReq req){
        passengerService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<List<PassengerQueryResp>> queryList(@Validated PassengerQueryReq req){
        req.setMemberId(LoginMemberContext.getId());
        List<PassengerQueryResp> list = passengerService.queryList(req);
        return new CommonResp<>(list);
    }

}
