package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectLayoutImage {
    /**
     * 户型图ID
     */
    private Integer layoutImgId;

    /**
     * 户型ID
     */
    private Integer layoutId;

    /**
     * 图片标题
     */
    private String imgTitle;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 图片描述
     */
    private String imgDesc;

    /**
     * 图片大小，单位：字节
     */
    private Integer imgSize;

    /**
     * 图片分辨率
     */
    private String imgResolution;

    /**
     * 图片上传人ID
     */
    private Integer creatorId;

    /**
     * 图片上传时间
     */
    private Date createTime;

}