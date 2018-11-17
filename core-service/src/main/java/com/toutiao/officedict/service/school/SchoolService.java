package com.toutiao.officedict.service.school;

import com.toutiao.officedict.dao.entity.officedict.School;
import com.toutiao.officedict.domain.query.SchoolQuery;

import java.util.List;

public interface SchoolService {


    /**
     * 根据条件查询学校列表
     * @param schoolQuery
     * @return
     */
    List<School> listSchool(SchoolQuery schoolQuery);

    /**
     * 根据id查询学校详情信息
     * @param schoolId
     * @return
     */
    School getSchoolById(Integer schoolId);

    /**
     * 新增学校信息
     * @param school
     * @return
     */
    int addSchool(School school);

    /**
     * 根据id删除学校信息
     * @param schoolId
     * @return
     */
    int delSchoolById(Integer schoolId);

    /**
     * 更新学校信息
     * @param school
     * @return
     */
    int updateSchool(School school);

}
