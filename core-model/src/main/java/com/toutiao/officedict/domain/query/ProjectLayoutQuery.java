package com.toutiao.officedict.domain.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author WuShoulei on 2017/12/2
 */
@Data
public class ProjectLayoutQuery {

    /**
     * 楼盘ID
     */
    @NotNull(message = "缺少楼盘Id")
    private Integer newcode;

    /**
     * 楼盘标题
     */
    private String layoutTitle;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;
}
