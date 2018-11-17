package com.toutiao.officedict.api.chance.request;

import lombok.Data;

import java.util.List;

@Data
public class BindingDistrictAreaRequest {

    /**
     * 区县ID
     */
    private Integer districtId;

    /**
     * 商圈ID
     */
    private List<Integer> areaIds;

    /**
     * 房屋类型
     */
    private Integer houseType;



}
