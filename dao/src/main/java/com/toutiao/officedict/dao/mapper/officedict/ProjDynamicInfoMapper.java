package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.DynamicInfo;
import com.toutiao.officedict.domain.query.ProjDynamicInfoQuery;

import java.util.List;

/**
 * Created by 18710 on 2018/4/17.
 */
public interface ProjDynamicInfoMapper extends BaseDao{
    List<DynamicInfo> dynamicInfoList(ProjDynamicInfoQuery projDynamicInfoQuery);
    Integer addDynamicInfo(ProjDynamicInfoQuery projDynamicInfoQuery);
    Integer removueDynamicInfo(Integer id);
    DynamicInfo selectOne(Integer id);
    Integer updateById(ProjDynamicInfoQuery projDynamicInfoQuery);
}
