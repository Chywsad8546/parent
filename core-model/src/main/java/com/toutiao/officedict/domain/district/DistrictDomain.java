package com.toutiao.officedict.domain.district;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DistrictDomain {
    /**
     * 区域ID
     */
    private Integer districtId;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 城市ID
     */
    private Long cityId;

    /**
     * 城市名称
     */
    private String cityName;

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

}