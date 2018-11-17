package com.toutiao.officedict.service.global.impl;

import com.toutiao.officedict.dao.entity.officedict.ResidenceBuildCategory;
import com.toutiao.officedict.dao.entity.officedict.ResidenceBuildForm;
import com.toutiao.officedict.dao.entity.officedict.ResidenceCategory;
import com.toutiao.officedict.dao.mapper.officedict.ResidenceBuildCategoryMapper;
import com.toutiao.officedict.dao.mapper.officedict.ResidenceBuildFormMapper;
import com.toutiao.officedict.dao.mapper.officedict.ResidenceCategoryMapper;
import com.toutiao.officedict.service.global.GlobalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WuShoulei on 2017/11/17
 */
@Service
public class GlobalConfigServiceImpl implements GlobalConfigService {

    @Autowired
    private ResidenceCategoryMapper residenceCategoryMapper;

    @Autowired
    private ResidenceBuildCategoryMapper buildCategoryMapper;

    @Autowired
    private ResidenceBuildFormMapper buildFormMapper;


    /**************************************住宅类别start****************************************************/

    @Override
    public List<ResidenceCategory> listResidenceCategory() {

        List<ResidenceCategory> residenceCategoryList = residenceCategoryMapper.queryResidenceCategoryList();

        return residenceCategoryList;
    }

    @Override
    public ResidenceCategory retrieveResidentialCategory(Integer categoryId) {
        return residenceCategoryMapper.selectByPrimaryKey(categoryId);
    }

    @Override
    public int addResidentialCategory(ResidenceCategory category) {
        return residenceCategoryMapper.insertSelective(category);
    }

    @Override
    public int updateResidentialCategory(ResidenceCategory category) {
        return residenceCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public int delResidentialCategory(Integer categoryId) {
        return residenceCategoryMapper.deleteByPrimaryKey(categoryId);
    }

    /**************************************住宅类别end****************************************************/

    /**************************************住宅建筑类别start****************************************************/


    @Override
    public List<ResidenceBuildCategory> listResidenceBuildCategory() {

        List<ResidenceBuildCategory> residenceBuildCategoryList = buildCategoryMapper.queryResidenceBuildCategoryList();

        return residenceBuildCategoryList;
    }

    @Override
    public ResidenceBuildCategory retrieveResidentialBuildCategory(Integer buildCategoryId) {
        return buildCategoryMapper.selectByPrimaryKey(buildCategoryId);
    }

    @Override
    public int addResidentialBuildCategory(ResidenceBuildCategory buildCategory) {
        return buildCategoryMapper.insertSelective(buildCategory);
    }

    @Override
    public int updateResidentialBuildCategory(ResidenceBuildCategory buildCategory) {
        return buildCategoryMapper.updateByPrimaryKeySelective(buildCategory);
    }

    @Override
    public int delResidentialBuildCategory(Integer buildCategoryId) {
        return buildCategoryMapper.deleteByPrimaryKey(buildCategoryId);
    }


    /**************************************住宅建筑类别end****************************************************/

    /**************************************住宅建筑形式start****************************************************/


    @Override
    public List<ResidenceBuildForm> listResidentialBuildForm() {

        List<ResidenceBuildForm> buildFormList = buildFormMapper.queryResidenceBuildFormList();

        return buildFormList;
    }

    @Override
    public ResidenceBuildForm retrieveResidentialBuildForm(Integer formId) {
        return buildFormMapper.selectByPrimaryKey(formId);
    }

    @Override
    public int addResidentialBuildForm(ResidenceBuildForm buildForm) {
        return buildFormMapper.insertSelective(buildForm);
    }

    @Override
    public int updateResidentialBuildForm(ResidenceBuildForm buildForm) {
        return buildFormMapper.updateByPrimaryKeySelective(buildForm);
    }

    @Override
    public int delResidentialBuildForm(Integer buildFormId) {
        return buildFormMapper.deleteByPrimaryKey(buildFormId);
    }

    /**************************************住宅建筑形式end****************************************************/

}
