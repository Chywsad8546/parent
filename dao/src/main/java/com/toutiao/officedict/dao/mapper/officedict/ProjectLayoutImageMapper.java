package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ProjectLayoutImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectLayoutImageMapper extends BaseDao {
    int deleteByPrimaryKey(Integer layoutImgId);

    int insert(ProjectLayoutImage record);

    int insertSelective(ProjectLayoutImage record);

    ProjectLayoutImage selectByPrimaryKey(Integer layoutImgId);

    int updateByPrimaryKeySelective(ProjectLayoutImage record);

    int updateByPrimaryKey(ProjectLayoutImage record);

    /**
     * 户型图列表
     * @param layoutId
     * @return
     */
    List<ProjectLayoutImage> listLayoutImages(@Param("layoutId") Integer layoutId);
}