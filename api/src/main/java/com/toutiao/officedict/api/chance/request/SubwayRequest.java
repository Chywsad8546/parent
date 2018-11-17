package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class SubwayRequest {

    /**
     * 地铁线ID
     */
    private Integer lineId;

    /**
     * 线路名称
     */
    private String subwayName;

    /**
     * 城市ID
     */
    private Integer cityId;

    /**
     * 排序
     */
    private Integer sorting;

    /**
     * 站点ID
     */
    private Integer stationId;

    /**
     * 站点名称
     */
    @NotEmpty(groups = {First.class},message = "地铁站名称不能为空")
    private String stationName;

    /**
     * x坐标
     */
    @NotNull(groups = {First.class},message = "X坐标不能为空")
    private BigDecimal coordX;

    /**
     * y坐标
     */
    @NotNull(groups = {First.class},message = "Y坐标不能为空")
    private BigDecimal coordY;

    /**
     * 是否换乘站（默认-否）
     0-否，1-是
     */
    private Short ischange;
}
