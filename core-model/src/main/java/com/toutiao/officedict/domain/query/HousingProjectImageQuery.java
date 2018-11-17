package com.toutiao.officedict.domain.query;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author WuShoulei on 2017/11/28
 */
@Data
public class HousingProjectImageQuery {
    /**
     * 图片的id
     */
    private Integer id;
    /**
     * 楼盘ID
     */
    @NotNull(groups = {First.class, Second.class}, message = "缺少楼盘Id")
    private Integer newcode;

    @NotNull(groups = First.class, message = "缺少户型Id")
    private Integer layoutId;

    /**
     * 图片类型（1-实景图，2-效果图，3-交通图，4-户型图，5-周边配套图，6-活动现场图等）
     */
    @NotNull(groups = { Second.class },message = "缺少图片类型")
    private Integer imgType;
    /**
     * 图片类型集合
     */
    private List<Integer> imgTypeArray;

    private String projname;

    private Integer isQualified;

    private Integer isDel;

    private Integer cityId;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;
}
