package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ActivityInfo;
import com.toutiao.officedict.domain.query.ActivityInfoQuery;

import java.util.List;

/**
 * Created by 18710 on 2018/9/17.
 */
public interface ProjActivityInfoMapper extends BaseDao {
    List<ActivityInfo> activityInfoList(ActivityInfoQuery activityInfoQuery);
    Integer addActivity(ActivityInfoQuery activityInfoQuery);
    ActivityInfo oneActivityInfo(Integer id);
    Integer updateById(ActivityInfoQuery activityInfoQuery);
    Integer removeActivityInfo(Integer id);
}
