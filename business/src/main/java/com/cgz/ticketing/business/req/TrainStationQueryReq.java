package com.cgz.ticketing.business.req;

import com.cgz.ticketing.common.req.PageReq;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TrainStationQueryReq extends PageReq {

    private String trainCode;
}
