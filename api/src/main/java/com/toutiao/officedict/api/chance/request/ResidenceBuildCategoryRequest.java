package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author WuShoulei on 2017/11/21
 */
@Data
public class ResidenceBuildCategoryRequest {

    /**
     * 建筑类别ID
     */
    @NotNull(groups = {First.class}, message = "缺少建筑类别ID")
    private Integer buildCategoryId;

    /**
     * 建筑类别名称
     */
    @NotNull(groups = Second.class,message = "缺少建筑类别名称")
    private String buildCategoryName;

    /**
     * 排序
     */
    private Integer sorting;
}
