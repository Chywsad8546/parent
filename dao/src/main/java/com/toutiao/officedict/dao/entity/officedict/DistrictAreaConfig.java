package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

@Data
public class DistrictAreaConfig {
    /**
     * 区县id
     */
    private Integer districtId;

    /**
     * 商圈id
     */
    private Integer areaId;

    /**
     * 房屋类型（1-新房，2-二手房）
     */
    private Integer houseType;

}