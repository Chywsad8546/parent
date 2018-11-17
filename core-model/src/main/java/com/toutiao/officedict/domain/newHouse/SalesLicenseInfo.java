package com.toutiao.officedict.domain.newHouse;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 预售证
 * [{"time": "2014-11-02 00:00:00", "buildInfo": "5#", "licenseName": "X京房权证朝字第1419387号"}]
 * @author WuShoulei on 2017/12/28
 */
@Data
public class SalesLicenseInfo {

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
