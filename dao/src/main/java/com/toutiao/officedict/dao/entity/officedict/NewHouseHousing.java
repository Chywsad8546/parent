package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class NewHouseHousing {
    /**
     * 房源ID
     */
    private Long houseId;

    /**
     * 所属项目ID
     */
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
    private Integer creatorId;

    /**
     * 创建
     */
    private Date createTime;

    /**
     * 更新者ID
     */
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