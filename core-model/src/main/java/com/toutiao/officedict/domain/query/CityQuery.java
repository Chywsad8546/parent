package com.toutiao.officedict.domain.query;

import lombok.Data;

/**
 * 城市查询
 *
 */
@Data
public class CityQuery {

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
     * 城市名称拼音
     */
    private String cityPinyin;
}
