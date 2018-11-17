package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class CitiesRequest {
    /**
     * 城市ID
     */
    @NotNull(groups = First.class, message = "城市id不能为空")
    private Integer cityId;

    /**
     * 城市名称
     */
    @NotEmpty(groups = {Second.class,First.class},message = "城市名称不能为空")
    private String cityName;

    /**
     * 所属大区
     */
    @NotEmpty(groups = {Second.class,First.class},message = "所属大区不能为空")
    private String belong;

    /**
     * 排序
     */
    private Integer sorting;

    /**
     * 城市名称拼音
     */
    @NotEmpty(groups = {Second.class,First.class},message = "城市名称拼音不能为空")
    private String cityPinyin;

    /**
     * 城市拼音首字母
     */
    @NotEmpty(groups = {Second.class,First.class},message = "城市拼音首字母不能为空")
    private String cityPinyinInitials;
}