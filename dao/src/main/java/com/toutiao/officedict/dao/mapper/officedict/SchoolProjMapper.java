package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.SchoolProj;

public interface SchoolProjMapper extends BaseDao {
    int deleteByPrimaryKey(Integer shId);

    int insert(SchoolProj record);

    int insertSelective(SchoolProj record);

    SchoolProj selectByPrimaryKey(Integer shId);

    int updateByPrimaryKeySelective(SchoolProj record);

    int updateByPrimaryKey(SchoolProj record);
}