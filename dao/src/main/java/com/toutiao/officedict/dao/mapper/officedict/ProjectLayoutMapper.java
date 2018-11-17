package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ProjectLayout;
import com.toutiao.officedict.domain.query.ProjectLayoutQuery;

import java.util.List;

public interface ProjectLayoutMapper extends BaseDao {
    int deleteByPrimaryKey(Integer layoutId);

    int insert(ProjectLayout record);

    int insertSelective(ProjectLayout record);

    ProjectLayout selectByPrimaryKey(Integer layoutId);

    int updateByPrimaryKeySelective(ProjectLayout record);

    int updateByPrimaryKey(ProjectLayout record);

    /**
     * 获取楼盘户型列表
     * @param layoutQuery
     * @return
     */
    List<ProjectLayout> listHousingProjectLayout(ProjectLayoutQuery layoutQuery);

    /**
     * 更新楼盘删除状态
     * @param layout
     * @return
     */
    int updateLayoutDeleteStatus(ProjectLayout layout);

}