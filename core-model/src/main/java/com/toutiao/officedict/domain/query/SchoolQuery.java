package com.toutiao.officedict.domain.query;

import lombok.Data;

@Data
public class SchoolQuery {

    /**
     * 当前页码
     */
    private Integer pageNum;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 学校类型:KG:幼儿园 L:小学 M:完中(初中+高中) JH:初中 NYS:九年一贯制(小学+初中) SH:高中 PS:职业学校 PES:成教学校 H:大学 S:未知
     */
    private String schoolType;

    /**
     * 学校名称
     */
    private String schoolName;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 区县ID
     */
    private Integer districtId;

    /**
     * 商圈ID
     */
    private Integer comareaId;

    /**
     * 学校级别
     */
    private String rank;

}
