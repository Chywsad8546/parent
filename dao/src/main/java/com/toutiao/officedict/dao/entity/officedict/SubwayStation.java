package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubwayStation {
    /**
     * 站点ID
     */
    private Integer stationId;

    /**
     * 站点名称
     */
    private String stationName;

    /**
     * 线路ID
     */
    private Integer lineId;

    /**
     * x坐标
     */
    private BigDecimal coordX;

    /**
     * y坐标
     */
    private BigDecimal coordY;

    /**
     * 排序
     */
    private Integer sorting;

    /**
     * 是否换乘站（默认-否）
 0-否，1-是
     */
    private Short ischange;
}