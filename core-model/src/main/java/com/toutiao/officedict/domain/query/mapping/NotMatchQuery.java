package com.toutiao.officedict.domain.query.mapping;

import lombok.Data;

/**
 * @author WuShoulei on 2018/7/16
 */
@Data
public class NotMatchQuery {

    private Integer id;

    private  String communityName;
    /**
     * 所属城市
     */
    private String city;

    /**
     * 所属城市编号
     */
    private Integer cityId;

    /**
     * 所属公司
     */
    private String company;

    /**
     * 当前页码，默认值为 1
     */
    private Integer pageNum;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 对应关系处理状态
     */
    private Integer status;
}
