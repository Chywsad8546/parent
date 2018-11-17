package com.toutiao.officedict.domain.query;


import lombok.Data;

@Data
public class DistrictQuery {

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
     * 城市名稱
     */
    private String cityName;

    /**
     * 区县ID
     */
    private Integer districtId;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 用戶當前城市
     */
    private Integer currCityId;
}
