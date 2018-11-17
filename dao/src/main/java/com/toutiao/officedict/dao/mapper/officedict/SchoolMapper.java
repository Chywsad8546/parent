package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.School;
import com.toutiao.officedict.domain.query.SchoolQuery;

import java.util.List;

public interface SchoolMapper extends BaseDao {
    int deleteByPrimaryKey(Integer schoolId);

    int insert(School record);

    int insertSelective(School record);

    School selectByPrimaryKey(Integer schoolId);

    int updateByPrimaryKeySelective(School record);

    int updateByPrimaryKey(School record);

    /**
     * 根据条件查询学校
     * @param schoolQuery
     * @return
     */
    List<School> selectSchoolList(SchoolQuery schoolQuery);
}