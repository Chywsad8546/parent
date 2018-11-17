package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Community {
    /**
     * 社区ID
     */
    private Integer communityId;

    /**
     * 社区名称
     */
    private String communityName;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 社区信息
     */
    private String communityInfo;

    /**
     * x坐标
     */
    private BigDecimal coordX;

    /**
     * y坐标
     */
    private BigDecimal coordY;

    /**
     * 社区拼音
     */
    private String communityPinyin;

    /**
     * 社区拼音首字母
     */
    private String communityPinyinInitials;

}