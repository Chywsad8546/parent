package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

@Data
public class ResidenceBuildForm {
    /**
     * 住宅建筑形式ID
     */
    private Integer formId;

    /**
     * 建筑形式名称
     */
    private String buildFormName;

    /**
     * 排序
     */
    private Integer sorting;
}