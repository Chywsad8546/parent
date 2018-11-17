package com.toutiao.officedict.api.chance.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author WuShoulei on 2018/2/8
 */
@Data
public class DynamicInfoRequest {

    @NotNull
    private Integer newcode;

    /**
     * 动态信息标题
     */
    private String title;

    /**
     * 动态信息详细信息
     */
    private String detail;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;
}
