package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author WuShoulei on 2017/11/24
 */
@Data
public class ProjPriceRequest {

    /**
     * 序号
     */
    @NotNull(groups = First.class, message = "缺少ID")
    private Integer id;

    /**
     * 楼盘ID
     */
    @NotNull(groups = Second.class, message = "缺少楼盘ID")
    private Integer newcode;

    /**
     * 销售类型
     */
    private String saleType;

    /**
     * 最高价
     */
    private BigDecimal priceMax;

    /**
     * 最高价价格单位
     */
    private String priceMaxType;

    /**
     * 平均价
     */
    private BigDecimal priceAverage;

    /**
     * 平均价价格单位
     */
    private String priceAverageType;

    /**
     * 最低价
     */
    private BigDecimal priceMin;

    /**
     * 最低价价格单位
     */
    private String priceMinType;

    /**
     * 价格描述
     */
    private String payDescription;

    private BigDecimal saleRate;

    /**
     * 工程进度
     */
    private String status;

    /**
     * 折扣日期
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date priceDate;

    /**
     * 折扣信息
     */
    private String saleInfo;

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
}
