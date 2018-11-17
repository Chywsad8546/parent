package com.toutiao.officedict.service.newhouse.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.DynamicInfo;
import com.toutiao.officedict.dao.mapper.officedict.ProjDynamicInfoMapper;
import com.toutiao.officedict.domain.query.ProjDynamicInfoQuery;
import com.toutiao.officedict.service.newhouse.ProjectDynamicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 18710 on 2018/4/17.
 */
@Service
public class ProjectDynamicInfoServiceImpl implements ProjectDynamicInfoService {
    @Autowired
    private ProjDynamicInfoMapper projDynamicInfoMapper;

    @Override
    public List<DynamicInfo> queryDynamicInfo(ProjDynamicInfoQuery projDynamicInfoQuery) {
        PageHelper.startPage(projDynamicInfoQuery.getPageNum(), projDynamicInfoQuery.getPageSize());
        List<DynamicInfo> dynamicInfoList =  projDynamicInfoMapper.dynamicInfoList(projDynamicInfoQuery);
        for (int i=0;i< dynamicInfoList.size();i++){
            SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd");
            String time = format0.format(dynamicInfoList.get(i).getTime());//这个就是把时间戳经过处理得到期望格式的时间
            dynamicInfoList.get(i).setShowTime(time);
        }
        return dynamicInfoList;
    }

    @Override
    public Integer addDynamicInfo(ProjDynamicInfoQuery projDynamicInfoQuery) {
        Date date = new Date();//获得系统时间.
        projDynamicInfoQuery.setCreateTime(date);
        projDynamicInfoQuery.setUpdateTime(date);
        return projDynamicInfoMapper.addDynamicInfo(projDynamicInfoQuery);
    }

    @Override
    public Integer removeDynamicInfo(Integer id) {
        return projDynamicInfoMapper.removueDynamicInfo(id);
    }

    @Override
    public DynamicInfo selectOne(Integer id) {
         return projDynamicInfoMapper.selectOne(id);
    }

    @Override
    public Integer updateByIde(ProjDynamicInfoQuery projDynamicInfoQuery) {
        Date date = new Date();//获得系统时间.
        projDynamicInfoQuery.setUpdateTime(date);
        return projDynamicInfoMapper.updateById(projDynamicInfoQuery);
    }
}
