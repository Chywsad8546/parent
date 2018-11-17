package com.toutiao.officedict.domain.query;

import lombok.Data;

@Data
public class DistrictAreaConfigQuery {

    /**
     * 区县ID
     */
    private Integer districtId;

    /**
     * 商圈ID
     */
    private Integer areaId;

    /**
     * 房屋类型
     */
    private Integer houseType;

    /**
     * 商圈拼音首字母
     */
    private String areaPinyinInitials;

    /**
     * 城市id
     */
    private Integer cityId;
}
