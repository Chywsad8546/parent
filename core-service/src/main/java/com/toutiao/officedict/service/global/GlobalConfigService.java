package com.toutiao.officedict.service.global;

import com.toutiao.officedict.dao.entity.officedict.ResidenceBuildCategory;
import com.toutiao.officedict.dao.entity.officedict.ResidenceBuildForm;
import com.toutiao.officedict.dao.entity.officedict.ResidenceCategory;

import java.util.List;

/**
 * @author WuShoulei on 2017/11/17
 */
public interface GlobalConfigService {

    /**************************************住宅类别start****************************************************/
    /**
     * 住宅类别列表
     * @return
     */
    List<ResidenceCategory> listResidenceCategory();

    /**
     * 获取某个住宅类别的详细信息
     * @param categoryId
     * @return
     */
    ResidenceCategory retrieveResidentialCategory(Integer categoryId);

    /**
     * 增加住宅类别
     * @param category
     * @return
     */
    int addResidentialCategory(ResidenceCategory category);

    /**
     * 更新住宅类别
     * @param category
     * @return
     */
    int updateResidentialCategory(ResidenceCategory category);

    /**
     * 删除住宅类别
     * @param categoryId
     */
    int delResidentialCategory(Integer categoryId);

    /**************************************住宅类别end****************************************************/

    /**************************************住宅建筑类别start****************************************************/
    /**
     * 住宅建筑类别列表
     * @return
     */
    List<ResidenceBuildCategory> listResidenceBuildCategory();

    /**
     * 获取某个住宅建筑类别的详细信息
     * @param buildCategoryId
     * @return
     */
    ResidenceBuildCategory retrieveResidentialBuildCategory(Integer buildCategoryId);

    /**
     * 增加住宅建筑类别
     * @param buildCategory
     * @return
     */
    int addResidentialBuildCategory(ResidenceBuildCategory buildCategory);

    /**
     * 更新住宅建筑类别
     * @param buildCategory
     * @return
     */
    int updateResidentialBuildCategory(ResidenceBuildCategory buildCategory);

    /**
     * 删除住宅建筑类别
     * @param id
     * @return
     */
    int delResidentialBuildCategory(Integer id);

    /**************************************住宅建筑类别end****************************************************/


    /**************************************住宅建筑形式start****************************************************/

    /**
     * 住宅建筑形式列表
     * @return
     */
    List<ResidenceBuildForm> listResidentialBuildForm();

    /**
     * 获取某个住宅建筑形式的详细信息
     * @param buildFormId
     * @return
     */
    ResidenceBuildForm retrieveResidentialBuildForm(Integer buildFormId);

    /**
     * 增加住宅建筑形式
     * @param buildForm
     * @return
     */
    int addResidentialBuildForm(ResidenceBuildForm buildForm);

    /**
     * 更新住宅建筑形式
     * @param buildForm
     * @return
     */
    int updateResidentialBuildForm(ResidenceBuildForm buildForm);

    /**
     * 删除住宅建筑形式
     * @param id
     * @return
     */
    int delResidentialBuildForm(Integer id);

    /**************************************住宅建筑形式end****************************************************/
}
