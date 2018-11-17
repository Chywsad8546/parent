package com.toutiao.officedict.domain.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author WuShoulei on 2017/11/25
 */
@Data
public class ProjectPriceQuery {

    /**
     * 楼盘ID
     */
    @NotNull(message = "缺少楼盘ID")
    Integer newcode;


    /**
     * 当前页码，默认值为 1
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;
}
