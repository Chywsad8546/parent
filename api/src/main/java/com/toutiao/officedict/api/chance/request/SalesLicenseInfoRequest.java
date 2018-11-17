package com.toutiao.officedict.api.chance.request;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author WuShoulei on 2017/12/29
 */
@Data
public class SalesLicenseInfoRequest {

    @NotNull
    private Integer newcode;

    /**
     * 预售证名称
     */
    private String licenseName;

    /**
     * 预售证对应楼栋
     */
    private String buildInfo;

    @JSONField(format = "yyyy-MM-dd")
    private Date time;
}
