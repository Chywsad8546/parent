package com.toutiao.officedict.domain.districtArea;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DistrictAreaDomain implements Serializable{

    /**
     * 区县id
     */
    private Integer districtId;

    /**
     * 区县名称
     */
    private String districtName;

    /**
     * 绑定商圈
     */
    private List<BindingArea> bindingAreas;


}
