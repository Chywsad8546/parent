package com.toutiao.officedict.domain.districtArea;

import lombok.Data;

/**
 * @author WuShoulei on 2017/12/2
 */
@Data
public class AreaWithDistrictID {
    /**
     * 商圈ID
     */
    private Integer areaId;

    /**
     * 商圈名称
     */
    private String areaName;

    private Integer districtID;

}
