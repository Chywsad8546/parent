package com.toutiao.officedict.api.chance.request;

import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 接收保存除户型图之外的其他图片请求
 * @author WuShoulei on 2017/11/22
 */
@Data
public class ProjectImageRequest {


    @NotNull(groups = {First.class, Second.class}, message = "缺少图片文件")
    private MultipartFile file;

    /**
     * 项目ID
     */
    @NotNull(groups = {First.class, Second.class}, message = "缺少楼盘ID")
    private Integer newcode;

    @NotNull(groups = First.class, message = "缺少户型ID")
    private Integer layoutId;

    /**
     * 图片类型（1-实景图，2-效果图，3-交通图，4-户型图，5-周边配套图，6-活动现场图等）
     */
    @NotNull(groups = {First.class, Second.class}, message = "缺少文件类型")
    private Integer imgType;

    /**
     * 图片路径
     */
    private String imgPath;

    /**
     * 标题
     */
    @NotNull(groups = {First.class, Second.class}, message = "缺少图片标题")
    private String title;

    /**
     * 图片描述
     */
    private String imgDesc;

    /**
     * 图片上传人ID
     */
    private Integer creatorId;

    /**
     * 图片上传时间
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
