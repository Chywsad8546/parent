package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ResidenceBuildForm;

import java.util.List;

public interface ResidenceBuildFormMapper extends BaseDao {
    int deleteByPrimaryKey(Integer formId);

    int insert(ResidenceBuildForm record);

    int insertSelective(ResidenceBuildForm record);

    ResidenceBuildForm selectByPrimaryKey(Integer formId);

    int updateByPrimaryKeySelective(ResidenceBuildForm record);

    int updateByPrimaryKey(ResidenceBuildForm record);

    /**
     * 查询住宅建筑形式列表
     * @return
     */
    List<ResidenceBuildForm> queryResidenceBuildFormList();
}