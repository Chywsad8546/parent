package com.toutiao.officedict.domain.districtArea;

import lombok.Data;

import java.util.List;

@Data
public class CityCascade {

    /**
     * 城市ID
     */
    private Integer value;

    /**
     * 城市名称
     */
    private String text;

    /**
     * 商圈集合
     */
    private List<DistrictCascade> list;
}
