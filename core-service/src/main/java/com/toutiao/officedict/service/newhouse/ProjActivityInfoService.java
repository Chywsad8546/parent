package com.toutiao.officedict.service.newhouse;

import com.toutiao.officedict.dao.entity.officedict.ActivityInfo;
import com.toutiao.officedict.domain.query.ActivityInfoQuery;

import java.util.List;

/**
 * Created by 18710 on 2018/9/17.
 */
public interface ProjActivityInfoService {
    List<ActivityInfo> queryActivityInfo(ActivityInfoQuery  activityInfoQuery);
    Integer addActivity (ActivityInfoQuery activityInfoQuery);
    ActivityInfo oneActivityInfo(Integer id);
    Integer updateById(ActivityInfoQuery activityInfoQuery);
    Integer removeActivityInfo(Integer id);
}
