package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class District {
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
    private Integer cityId;

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