package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class School {
    /**
     * 学校ID
     */
    private Integer schoolId;

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
     * 维度坐标
     */
    private BigDecimal coordX;

    /**
     * 经度坐标
     */
    private BigDecimal coordY;

    /**
     * 区县ID
     */
    private Integer districtId;

    /**
     * 商圈ID
     */
    private Integer comareaId;

    /**
     * 地址
     */
    private String address;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 邮编
     */
    private Integer code;

    /**
     * 电话
     */
    private String tele;

    /**
     * 学校类别
     */
    private String nature;

    /**
     * 学校级别
     */
    private String rank;

    /**
     * 学校主页
     */
    private String homePage;

    /**
     * 学校特色
     */
    private String feature;

    /**
     * 学校图片
     */
    private String imgUrl;

    /**
     * 是否展示,0-否，1-是
     */
    private Short isShow;

    /**
     * 创建人ID
     */
    private Integer creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人ID
     */
    private Integer updaterId;

    /**
     * 更新时间
     */
    private Date updateTime;

}