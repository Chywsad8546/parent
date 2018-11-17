package com.toutiao.officedict.domain.query;

import lombok.Data;

/**
 * @author WuShoulei on 2017/11/21
 */
@Data
public class HousingQuery {

    /**
     * 当前页码，默认值为 1
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
     * 区县ID
     */
    private Integer districtId;

    /**
     * 商圈ID
     */
    private Integer areaId;

    /**
     * 搜索词
     */
    private String keyword;
}
