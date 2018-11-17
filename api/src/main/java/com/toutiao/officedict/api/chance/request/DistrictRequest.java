package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class DistrictRequest {

    /**
     * 区域ID
     */
    @NotNull(groups = Second.class,message = "区县ID不能为空")
    private Integer districtId;

    /**
     * 区县名称
     */
    @NotEmpty(groups = {First.class,Second.class},message = "区县名称不能为空")
    private String districtName;

    /**
     * 城市ID
     */
    @NotNull(groups = {First.class,Second.class},message = "城市不能为空")
    private Integer cityId;

    /**
     * x坐标
     */

//    @NotNull(groups = {First.class,Second.class},message = "区县x坐标不能为空")
    private BigDecimal coordX;

    /**
     * y坐标
     */
//    @NotNull(groups = {First.class,Second.class},message = "区县y坐标不能为空")
    private BigDecimal coordY;

    /**
     * 排序
     */
    private Integer sorting;



}
