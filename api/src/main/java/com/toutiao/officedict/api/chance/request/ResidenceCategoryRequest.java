package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author WuShoulei on 2017/11/20
 */
@Data
public class ResidenceCategoryRequest {

    /**
     * 类别ID
     */
    @NotNull(groups = {First.class}, message = "缺少类别ID")
    private Integer categoryId;

    /**
     * 类别名称
     */
    @NotNull(groups = {Second.class}, message = "住宅类别不能为空")
    private String categoryName;

    /**
     * 排序
     */
    private Integer sorting;
}
