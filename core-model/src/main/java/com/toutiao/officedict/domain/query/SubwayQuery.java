package com.toutiao.officedict.domain.query;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SubwayQuery {

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 地铁线ID
     */
    private Integer lineId;

    /**
     * 站点ID
     */
    private Integer stationId;

    /**
     * x坐标
     */
    private BigDecimal coordX;

    /**
     * y坐标
     */
    private BigDecimal coordY;

    /**
     * 站点名称
     */
    private String stationName;
}
