package com.toutiao.officedict.service.school.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.School;
import com.toutiao.officedict.dao.mapper.officedict.SchoolMapper;
import com.toutiao.officedict.domain.query.SchoolQuery;
import com.toutiao.officedict.service.school.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService{

    @Autowired
    private SchoolMapper schoolMapper;


    @Override
    public List<School> listSchool(SchoolQuery schoolQuery) {

        if (schoolQuery.getPageNum() == null || schoolQuery.getPageSize() == 0 || schoolQuery.getPageSize() == null) {
            schoolQuery.setPageNum(1);
            schoolQuery.setPageSize(10);
            PageHelper.startPage(schoolQuery.getPageNum(), schoolQuery.getPageSize());
        }else{
            PageHelper.startPage(schoolQuery.getPageNum(), schoolQuery.getPageSize());
        }
        List<School> schoolList = schoolMapper.selectSchoolList(schoolQuery);
        return schoolList;
    }

    @Override
    public School getSchoolById(Integer schoolId) {
        School school = schoolMapper.selectByPrimaryKey(schoolId);
        return school;
    }

    @Override
    public int addSchool(School school) {
        return 0;
    }

    @Override
    public int delSchoolById(Integer schoolId) {
        return 0;
    }

    @Override
    public int updateSchool(School school) {
        return 0;
    }
}
