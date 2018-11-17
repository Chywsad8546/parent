package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

@Data
public class ResidenceBuildCategory {
    /**
     * 建筑类别ID
     */
    private Integer buildCategoryId;

    /**
     * 建筑类别名称
     */
    private String buildCategoryName;

    /**
     * 排序
     */
    private Integer sorting;
}