package com.toutiao.officedict.api.chance.request.relation;

import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * Created by 18710 on 2018/4/11.
 */
@Data
public class RelationOfMaiTianRequest {
    /**
     * 主键Id
     */
    @NotNull(groups = Second.class,message = "id不能为空")
    private Integer id;

    /**
     * 头条房产楼盘名
     */
    private String ttfcProjectName;

    /**
     * 麦田楼盘名
     */
    private String maitianProjectName;
}
