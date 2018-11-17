package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ResidenceBuildCategory;

import java.util.List;

public interface ResidenceBuildCategoryMapper extends BaseDao {
    int deleteByPrimaryKey(Integer buildCategoryId);

    int insert(ResidenceBuildCategory record);

    int insertSelective(ResidenceBuildCategory record);

    ResidenceBuildCategory selectByPrimaryKey(Integer buildCategoryId);

    int updateByPrimaryKeySelective(ResidenceBuildCategory record);

    int updateByPrimaryKey(ResidenceBuildCategory record);

    /**
     * 获取住宅建筑类别列表
     * @return
     */
    List<ResidenceBuildCategory> queryResidenceBuildCategoryList();
}