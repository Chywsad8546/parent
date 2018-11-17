package com.toutiao.officedict.api.chance.request.newhouse;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WuShoulei on 2017/12/2
 */
@Data
public class ProjectLayoutRequest {

    /**
     * 户型序号
     */
    @NotNull(groups = {First.class, Third.class}, message = "缺少户型ID")
    private Integer layoutId;

    /**
     * 楼盘ID
     */
    @NotNull(groups = {Second.class}, message = "缺少楼盘ID")
    private Integer newcode;

    /**
     * 室
     */
    private Short room;

    /**
     * 厅
     */
    private Short hall;

    /**
     * 卫
     */
    private Short toilet;

    /**
     * 厨
     */
    private Short kitchen;

    /**
     * 建筑面积
     */
    private BigDecimal buildingArea;

    /**
     * 户型描述/户型解析
     */
    private String layoutDesc;

    /**
     * 居住面积
     */
    private BigDecimal livingArea;

    /**
     * 销售面积
     */
    private BigDecimal saleArea;

    /**
     * 户型标题
     */
    @NotNull(groups = {First.class, Second.class}, message = "缺少户型标题")
    private String layoutTitle;

    /**
     * 标签
     */
    private String[] tag;

    /**
     * 创建人ID
     */
    private Integer creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人ID
     */
    private Integer updaterId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 参考均价
     */
    private BigDecimal referencePrice;

    /**
     * 参考总价
     */
    private BigDecimal referenceTotalPrice;

    /**
     * 是否主推(0-否，1-是)
     */
    private Short isRecommend;

    private Integer salesStatus;
}
