package com.cgz.ticketing.business.controller;

import com.cgz.ticketing.business.req.StationQueryReq;
import com.cgz.ticketing.business.req.StationSaveReq;
import com.cgz.ticketing.business.resp.StationQueryResp;
import com.cgz.ticketing.business.service.StationService;
import com.cgz.ticketing.common.resp.CommonResp;
import com.cgz.ticketing.common.resp.PageResp;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/station")
public class StationController {

    @Resource
    private StationService stationService;

    @PostMapping("/save")
    public CommonResp<Object> save(@Valid @RequestBody StationSaveReq req) {
        stationService.save(req);
        return new CommonResp<>();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<StationQueryResp>> queryList(@Valid StationQueryReq req) {
        PageResp<StationQueryResp> list = stationService.queryList(req);
        return new CommonResp<>(list);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id) {
        stationService.delete(id);
        return new CommonResp<>();
    }

}