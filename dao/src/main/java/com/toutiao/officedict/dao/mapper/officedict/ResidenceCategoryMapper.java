package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ResidenceCategory;

import java.util.List;

public interface ResidenceCategoryMapper extends BaseDao {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(ResidenceCategory record);

    int insertSelective(ResidenceCategory record);

    ResidenceCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(ResidenceCategory record);

    int updateByPrimaryKey(ResidenceCategory record);

    /**
     * 获取住宅类别列表
     * @return
     */
    List<ResidenceCategory> queryResidenceCategoryList();
}