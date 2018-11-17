package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.util.Date;

@Data
public class ProjImage {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 楼盘ID
     */
    private Integer newcode;

    /**
     * 图片类型（1-实景图，2-效果图，3-交通图，4-户型图，5-周边配套图，6-活动现场图等）
     */
    private Integer imgType;

    private String projname;


    /**
     * 是否合格
     */
    private Integer isQualified;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片描述
     */
    private String imgDesc;

    /**
     * 图片大小
     */
    private Integer imgSize;

    /**
     * 图片分辨率
     */
    private String imgResolution;

    /**
     * 图片后缀
     */
    private String  imgSuffix;

    /**
     * 图片上传人ID
     */
    private Integer creatorId;

    /**
     * 图片上传时间
     */
    private Date createTime;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 更新人ID
     */
    private Integer updaterId;

    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否为标题图
     */
    private Integer isLogPic;
    /**
     * 标记是否已存在标题图
     * 0-不存在，1为已存在
     */
    private Integer remark;
}