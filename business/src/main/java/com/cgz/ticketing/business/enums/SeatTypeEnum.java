package com.cgz.ticketing.business.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public enum SeatTypeEnum {

    YDZ("1", "一等座", new BigDecimal("0.4")),
    EDZ("2", "二等座", new BigDecimal("0.3")),
    RW("3", "软卧", new BigDecimal("0.6")),
    YW("4", "硬卧", new BigDecimal("0.5"));

    private final String code;

    private final String desc;

    /**
     * 基础票价 N元/公里，0.4即为0.4元/公里
     */
    private final BigDecimal price;

    public static List<HashMap<String,String>> getEnumList() {
        List<HashMap<String, String>> list = new ArrayList<>();
        for (SeatTypeEnum anEnum : EnumSet.allOf(SeatTypeEnum.class)) {
            HashMap<String, String> map = new HashMap<>();
            map.put("code",anEnum.code);
            map.put("desc",anEnum.desc);
            list.add(map);
        }
        return list;
    }
}
