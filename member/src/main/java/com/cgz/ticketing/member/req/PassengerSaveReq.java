package com.cgz.ticketing.member.req;

import lombok.Data;

import java.util.Date;

@Data
public class PassengerSaveReq {
    private Long id;

    private Long memberId;

    private String name;

    private String idCard;

    private String type;

    private Date createTime;

    private Date updateTime;
}