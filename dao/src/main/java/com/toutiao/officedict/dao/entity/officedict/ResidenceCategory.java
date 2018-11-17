package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

@Data
public class ResidenceCategory {
    /**
     * 类别ID
     */
    private Integer categoryId;

    /**
     * 类别名称
     */
    private String categoryName;

    /**
     * 排序
     */
    private Integer sorting;
}