package com.toutiao.officedict.domain.query;


import lombok.Data;

@Data
public class AreaQuery {

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    /**
     * 商圈ID
     */
    private Integer areaId;

    /**
     * 商圈名称
     */
    private String areaName;

    /**
     * 商圈拼音首字母
     */
    private String areaPinyinInitials;

    /**
     * 住宅类型
     */
    private Integer houseType;

    /**
     * 区县ID
     */
    private Integer districtId;

    private Integer currentCityId;
}
