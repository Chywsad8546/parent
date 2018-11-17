package com.toutiao.officedict.api.chance.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author WuShoulei on 2017/12/22
 */
@Data
public class LayoutImageRequest {

    @NotNull(message = "缺少图片文件")
    private MultipartFile file;

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
