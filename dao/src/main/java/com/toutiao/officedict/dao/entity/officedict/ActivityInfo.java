package com.toutiao.officedict.dao.entity.officedict;

import lombok.Data;

import java.util.Date;

/**
 * Created by 18710 on 2018/9/17.
 */
@Data
public class ActivityInfo {
    Integer id;

    Integer activityType;

    String activityDesc;

    String activityTitle;

    String activitySubtitle;

    Integer creatorId;

    Date createTime;

    Integer updaterId;

    Date updateTime;

    Integer isDel;

    Integer newcode;

    Integer isActivity;
}
