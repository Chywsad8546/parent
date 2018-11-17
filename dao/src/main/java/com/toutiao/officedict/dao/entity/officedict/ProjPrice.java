package com.toutiao.officedict.dao.entity.officedict;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProjPrice {
    /**
     * 序号
     */
    private Integer id;

    /**
     * 楼盘ID
     */
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

    /**
     * 销售率
     */
    private BigDecimal saleRate;

    /**
     * 工程进度
     */
    private String status;

    /**
     * 报价日期
     */
    @JSONField(format = "yyyy-MM-dd")
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