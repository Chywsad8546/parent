package com.toutiao.officedict.domain.newHouse;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 * 楼盘动态
 * [{"time": "2018-02-03 15:19:42", "title": "中海寰宇天下一期已于2018年2月3日开盘", "detail": "中海寰宇天下一期景山府已于2018年2月3日开盘，
 * 均价61000元/㎡。中海寰宇天下项目位于北京市石景山区阜石路与北辛安路交叉口南，户型为85平米两居室，97至125平米三居室"}]
 * @author WuShoulei on 2018/2/8
 */
@Data
public class SalesDynamicInfo {

    /**
     * 标题
     */
    private String title;

    /**
     * 详细信息
     */
    private String detail;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;
}
