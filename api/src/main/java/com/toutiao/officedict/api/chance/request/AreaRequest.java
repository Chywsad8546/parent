package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AreaRequest {
    /**
     * 商圈ID
     */
    @NotNull(groups = Second.class,message = "商圈ID不能为空")
    private Integer areaId;

    /**
     * 商圈名称
     */
    @NotEmpty(groups = {First.class, Second.class},message = "商圈名称不能为空")
    private String areaName;

    /**
     * 城市ID
     */
    @NotNull(groups = {First.class, Second.class},message = "城市ID不能为空")
    private Integer cityId;

    /**
     * x坐标
     */
   // @NotNull(groups =  {First.class, Second.class},message = "x坐标不能为空")
    private BigDecimal coordX;

    /**
     * y坐标
     */
    //@NotNull(groups =  {First.class, Second.class},message = "y坐标不能为空")
    private BigDecimal coordY;

    /**
     * 商圈名称拼音
     */
    @NotNull(groups =  {First.class, Second.class},message = "商圈名称拼音不能为空")
    private String areaPinyin;

    /**
     * 商圈拼音首字母
     */
    @NotNull(groups =  {First.class, Second.class},message = "商圈拼音首字母不能为空")
    private String areaPinyinInitials;


}