package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author WuShoulei on 2017/11/21
 */
@Data
public class ResidenceBuildFormRequest {

    /**
     * 住宅建筑形式ID
     */
    @NotNull(groups = First.class, message = "缺少住宅建筑形式ID")
    private Integer formId;

    /**
     * 建筑形式名称
     */
    @NotNull(groups = Second.class, message = "缺少住宅建筑形式名称")
    private String buildFormName;

    /**
     * 排序
     */
    private Integer sorting;
}
