package com.toutiao.officedict.api.chance.request.newhouse;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WuShoulei on 2017/11/21
 */
@Data
public class NewHouseHousingRequest {

    /**
     * 房源ID
     */
    @NotNull(groups = {First.class, Third.class}, message = "缺少房源ID")
    private Long houseId;

    /**
     * 所属项目ID
     */
    @NotNull(groups = {Second.class}, message = "缺少楼盘ID")
    private Integer projId;

    /**
     * 室
     */
    private Short room;

    /**
     * 厅
     */
    private Short hall;

    /**
     * 厨
     */
    private Short kitchen;

    /**
     * 卫
     */
    private Short toilet;

    /**
     * 阳台
     */
    private Short balcony;

    /**
     * 朝向
     */
    private String forward;

    /**
     * 户型（1居/2居/3居）
     */
    @NotNull(groups = {Second.class}, message = "缺少户型信息")
    private String houseLayout;

    /**
     * 楼层
     */
    private Short floor;

    /**
     * 总楼层
     */
    private String totalFloor;

    /**
     * 单价
     */
    private Double price;

    /**
     * 总价
     */
    private Double totalPrice;

    /**
     * 楼栋
     */
    private Integer buildNo;

    /**
     * 销售状态
     */
    private String salesStatus;

    /**
     * 单元
     */
    private Short unitNo;

    /**
     * 房号
     */
    private Integer roomNo;

    /**
     * 建筑面积
     */
    private BigDecimal buildArea;

    /**
     * 使用面积
     */
    private BigDecimal liveArea;

    /**
     * 创建人ID
     */
    @NotNull(groups = {Second.class}, message = "缺少创建人信息")
    private Integer creatorId;

    /**
     * 创建
     */
    private Date createTime;

    /**
     * 更新者ID
     */
    @NotNull(groups = {First.class}, message = "缺少更新人信息")
    private Integer updaterId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除(0-否，1-是)
     */
    private Short isDel;

    /**
     * 状态(0-未发布，1-已发布)
     */
    private Short status;
}
