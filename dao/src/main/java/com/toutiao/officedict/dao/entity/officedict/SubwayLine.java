package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

@Data
public class SubwayLine {
    /**
     * 地铁线ID
     */
    private Integer lineId;

    /**
     * 线路名称
     */
    private String subwayName;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 排序
     */
    private Integer sorting;
}