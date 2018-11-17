package com.toutiao.officedict.service.newhouse;

import com.toutiao.officedict.dao.entity.officedict.DynamicInfo;
import com.toutiao.officedict.domain.query.ProjDynamicInfoQuery;

import java.util.List;

/**
 * Created by 18710 on 2018/4/17.
 */
public interface ProjectDynamicInfoService {
    List<DynamicInfo> queryDynamicInfo(ProjDynamicInfoQuery projDynamicInfoQuery);
    Integer addDynamicInfo(ProjDynamicInfoQuery projDynamicInfoQuery);
    Integer removeDynamicInfo(Integer id);
    DynamicInfo selectOne(Integer id);
    Integer updateByIde(ProjDynamicInfoQuery projDynamicInfoQuery);
}
