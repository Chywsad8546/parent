package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.util.Date;

/**
 * Created by 18710 on 2018/4/17.
 */
@Data
public class DynamicInfo {
    Integer id;
    Integer type;
    Date time;
    String showTime;
    String title;
    String detail;
    Integer creatorId;
    Date createTime;
    Integer updaterId;
    Date updateTime;
    String linkUrl;
    Integer newcode;
    Integer isDel;
    Integer cityId;
}
