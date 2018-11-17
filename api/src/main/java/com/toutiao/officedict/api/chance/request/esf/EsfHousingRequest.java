package com.toutiao.officedict.api.chance.request.esf;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WuShoulei on 2017/11/21
 */
@Data
public class EsfHousingRequest {

    /**
     * 房源ID
     */
    @NotNull(groups = First.class, message = "缺少房源ID")
    private Long houseId;

    /**
     * 项目ID(楼盘/小区)
     */
    @NotNull(groups = Second.class, message = "缺少楼盘ID")
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
     * 所在楼层(地下室填写负数)
     */
    private Short floor;

    /**
     * 总的楼层数
     */
    private Short totalFloor;

    /**
     * 建筑面积
     */
    private BigDecimal buildArea;

    /**
     * 使用面积
     */
    private BigDecimal liveArea;

    /**
     * 朝向
     */
    private String forward;

    /**
     * 总价
     */
    private Double totalPrice;

    /**
     * 装修(毛坯/简装/精装/豪装)
     */
    private String fitment;

    /**
     * 产权性质(个人产权/经适房/单位产权/限价房/军产房)
     */
    private String propertyRight;

    /**
     * 房屋类型：普通住宅、经济适用房、公寓、安置房、四合院等
     */
    private String houseType;

    /**
     * 房源标题
     */
    private String houseTitle;

    /**
     * 房源描述
     */
    private String houseDesc;

    /**
     * 物业类型：住宅、公寓、洋房、别墅等
     */
    private String propertyType;

    /**
     * 房源信息来源（编辑录入/导入）
     */
    private String source;

    /**
     * 创建人ID
     */
    @NotNull(groups = Second.class, message = "缺少创建人信息")
    private Integer creatorId;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人ID
     */
    @NotNull(groups = First.class, message = "缺少更新人信息")
    private Integer updaterId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否删除(0-未删除/1-已删除)
     */
    private Short isDel;

    /**
     * 状态(0-未发布/1-已发布)
     */
    private Short status;

    /**
     * 单价
     */
    private Double price;
}
