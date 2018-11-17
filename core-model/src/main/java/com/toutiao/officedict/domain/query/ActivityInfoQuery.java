package com.toutiao.officedict.domain.query;

import lombok.Data;

import java.util.Date;

/**
 * Created by 18710 on 2018/9/17.
 */
@Data
public class ActivityInfoQuery {
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
    /**
     * 当前页码，默认值为 1
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;
}
