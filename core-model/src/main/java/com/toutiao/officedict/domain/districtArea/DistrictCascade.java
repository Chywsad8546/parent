package com.toutiao.officedict.domain.districtArea;

import lombok.Data;

import java.util.List;

@Data
public class DistrictCascade {

    /**
     * 区域ID
     */
    private Integer value;

    /**
     * 区县名称
     */
    private String text;

    /**
     * 商圈集合
     */
    private List<AreaCascade> list;
}
