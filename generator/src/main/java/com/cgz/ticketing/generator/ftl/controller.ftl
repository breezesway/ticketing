package com.cgz.ticketing.member.controller;

import com.cgz.ticketing.common.context.LoginMemberContext;
import com.cgz.ticketing.common.resp.CommonResp;
import com.cgz.ticketing.common.resp.PageResp;
import com.cgz.ticketing.member.req.${Domain}QueryReq;
import com.cgz.ticketing.member.req.${Domain}SaveReq;
import com.cgz.ticketing.member.resp.${Domain}QueryResp;
import com.cgz.ticketing.member.service.${Domain}Service;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/${do_main}")
public class ${Domain}Controller {

    @Resource
    private ${Domain}Service ${domain}Service;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody ${Domain}SaveReq req) {
        ${domain}Service.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<${Domain}QueryResp>> queryList(@Valid ${Domain}QueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<${Domain}QueryResp> list = ${domain}Service.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        ${domain}Service.delete(id);
        return new CommonResp<>();
    }

}