package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Area {
    /**
     * 商圈ID
     */
    private Integer areaId;

    /**
     * 商圈名称
     */
    private String areaName;

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
     * 商圈名称拼音
     */
    private String areaPinyin;

    /**
     * 商圈拼音首字母
     */
    private String areaPinyinInitials;


}