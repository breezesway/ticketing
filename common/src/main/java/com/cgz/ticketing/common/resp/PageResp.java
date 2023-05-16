package com.cgz.ticketing.common.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class PageResp<T> implements Serializable {
    /**
     * 总条数
     */
    private Long total;

    /**
     * 当前页的列表
     */
    private List<T> list;
}