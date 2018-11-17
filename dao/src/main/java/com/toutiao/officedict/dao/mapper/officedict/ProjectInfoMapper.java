package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ProjectInfo;
import com.toutiao.officedict.domain.query.HousingProjectQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectInfoMapper extends BaseDao {
    int deleteByPrimaryKey(Integer newcode);

    int insert(ProjectInfo record);

    int insertSelective(ProjectInfo record);

    ProjectInfo selectByPrimaryKey(Integer newcode);

    int updateByPrimaryKeySelective(ProjectInfo record);

    int updateByPrimaryKey(ProjectInfo record);

    /**
     * 查询楼盘列表
     * @param query
     * @return
     */
    List<ProjectInfo> selectProjInfoList(HousingProjectQuery query);

    /**
     * 删除楼盘-逻辑删除
     * @param newcode
     * @return
     */
    int updateProjInfoDelStatus(Integer newcode);

    /**
     * 逻辑删除楼盘
     * @param projInfo
     * @return
     */
    int logicDelProj(ProjectInfo projInfo);

    /**
     * 根据楼盘名称查询楼盘信息
     * 楼盘名称，完全匹配
     * @param projName
     * @return
     */
    ProjectInfo selectProjectByName(@Param("projname") String projName);

    Integer updateIsActivity(ProjectInfo projectInfo);
}