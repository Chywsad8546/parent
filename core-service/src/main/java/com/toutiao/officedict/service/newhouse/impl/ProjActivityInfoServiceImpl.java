package com.toutiao.officedict.service.newhouse.impl;

import com.toutiao.officedict.dao.entity.officedict.ActivityInfo;
import com.toutiao.officedict.dao.mapper.officedict.ProjActivityInfoMapper;
import com.toutiao.officedict.domain.query.ActivityInfoQuery;
import com.toutiao.officedict.service.newhouse.ProjActivityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 18710 on 2018/9/17.
 */
@Service
@Transactional
public class ProjActivityInfoServiceImpl implements ProjActivityInfoService {
    @Autowired
    private ProjActivityInfoMapper projActivityInfoMapper;
    @Override
    public List<ActivityInfo> queryActivityInfo(ActivityInfoQuery projDynamicInfoQuery) {
        return projActivityInfoMapper.activityInfoList(projDynamicInfoQuery);
    }

    @Override
    public Integer addActivity(ActivityInfoQuery projDynamicInfoQuery) {
        return projActivityInfoMapper.addActivity(projDynamicInfoQuery);
    }

    @Override
    public ActivityInfo oneActivityInfo(Integer id) {
        return projActivityInfoMapper.oneActivityInfo(id);
    }

    @Override
    public Integer updateById(ActivityInfoQuery activityInfoQuery) {
        return projActivityInfoMapper.updateById(activityInfoQuery);
    }

    @Override
    public Integer removeActivityInfo(Integer id) {
        return projActivityInfoMapper.removeActivityInfo(id);
    }
}
