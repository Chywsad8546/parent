package com.toutiao.officedict.apiimpl.controller.global;

import com.toutiao.officedict.api.chance.request.ResidenceBuildCategoryRequest;
import com.toutiao.officedict.api.chance.request.ResidenceBuildFormRequest;
import com.toutiao.officedict.api.chance.request.ResidenceCategoryRequest;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.ResidenceBuildCategory;
import com.toutiao.officedict.dao.entity.officedict.ResidenceBuildForm;
import com.toutiao.officedict.dao.entity.officedict.ResidenceCategory;
import com.toutiao.officedict.service.global.GlobalConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 基础信息配置
 * @author WuShoulei on 2017/11/17
 */
@RestController
@RequestMapping("/basic")
public class GlobalConfigRestful {

    @Autowired
    private GlobalConfigService configService;


    /**************************************住宅类别start****************************************************/

    /**
     * 住宅类别列表
     * @return
     */
    @RequestMapping(value = "/listResidentialCategory")
    @ResponseBody
    public NashResult listResidentialCategory() {

        List<ResidenceCategory> residenceCategoryList = configService.listResidenceCategory();
        return NashResult.build(residenceCategoryList);
    }

    /**
     * 查询某个住宅类别的详细信息
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/getResidentialCategory")
    @ResponseBody
    public NashResult getResidentialCategory(@RequestParam(value = "id") Integer categoryId) {

        return NashResult.build(configService.retrieveResidentialCategory(categoryId));
    }

    /**
     * 添加住宅类别
     * @param categoryRequest
     * @return
     */
    @RequestMapping(value = "/saveResidentialCategory", method = RequestMethod.POST)
    @ResponseBody
    public NashResult addResidentialCategory (@Validated({Second.class}) ResidenceCategoryRequest categoryRequest) {

        ResidenceCategory category = new ResidenceCategory();
        BeanUtils.copyProperties(categoryRequest, category);

        return NashResult.build(configService.addResidentialCategory(category));
    }

    /**
     * 更新住宅类别
     * @param categoryRequest
     * @return
     */
    @RequestMapping(value = "/updateResidentialCategory", method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateResidentialCategory (@Validated({First.class}) ResidenceCategoryRequest categoryRequest) {

        ResidenceCategory category = new ResidenceCategory();
        BeanUtils.copyProperties(categoryRequest, category);

        return NashResult.build(configService.updateResidentialCategory(category));
    }

    /**
     * 删除住宅类别
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/deleteResidentialCategory")
    @ResponseBody
    public NashResult delResidentialCategory (@RequestParam("id") Integer categoryId) {

        return NashResult.build(configService.delResidentialCategory(categoryId));
    }

    /**************************************住宅类别end****************************************************/

    /**************************************住宅建筑类别start****************************************************/

    /**
     * 住宅建筑类别列表
     * @return
     */
    @RequestMapping(value = "/listResidentialBuildCategory")
    @ResponseBody
    public NashResult listResidentialBuildCategory() {

        List<ResidenceBuildCategory> residenceBuildCategoryList = configService.listResidenceBuildCategory();
        return NashResult.build(residenceBuildCategoryList);
    }

    /**
     * 查询某个住宅建筑类别的详细信息
     * @param buildCategoryId
     * @return
     */
    @RequestMapping(value = "/getResidentialBuildCategory")
    @ResponseBody
    public NashResult getResidentialBuildCategory(@RequestParam(value = "id") Integer buildCategoryId) {

        return NashResult.build(configService.retrieveResidentialBuildCategory(buildCategoryId));
    }

    /**
     * 添加住宅建筑类别
     * @param buildCategoryRequest
     * @return
     */
    @RequestMapping(value = "/saveResidentialBuildCategory", method = RequestMethod.POST)
    @ResponseBody
    public NashResult addResidentialBuildCategory (@Validated(Second.class) ResidenceBuildCategoryRequest buildCategoryRequest) {

        ResidenceBuildCategory buildCategory = new ResidenceBuildCategory();
        BeanUtils.copyProperties(buildCategoryRequest, buildCategory);

        return NashResult.build(configService.addResidentialBuildCategory(buildCategory));
    }

    /**
     * 更新住宅建筑类别
     * @param buildCategoryRequest
     * @return
     */
    @RequestMapping(value = "/updateResidentialBuildCategory", method = RequestMethod.POST)
    public NashResult updateResidentialBuildCategory (@Validated(First.class) ResidenceBuildCategoryRequest buildCategoryRequest) {

        ResidenceBuildCategory buildCategory = new ResidenceBuildCategory();
        BeanUtils.copyProperties(buildCategoryRequest, buildCategory);

        return NashResult.build(configService.updateResidentialBuildCategory(buildCategory));
    }

    /**
     * 删除住宅建筑类别
     * @param buildCategoryId
     * @return
     */
    @RequestMapping(value = "/deleteResidentialBuildCategory")
    @ResponseBody
    public NashResult delResidentialBuildCategory (@RequestParam("id") Integer buildCategoryId) {
        return NashResult.build(configService.delResidentialBuildCategory(buildCategoryId));
    }

    /**************************************住宅建筑类别start****************************************************/


    /**************************************住宅建筑形式start****************************************************/

    /**
     * 住宅建筑形式列表
     * @return
     */
    @RequestMapping(value = "/listResidentialBuildForm")
    @ResponseBody
    public NashResult listResidentialBuildForm() {

        List<ResidenceBuildForm> residenceBuildFormList = configService.listResidentialBuildForm();
        return NashResult.build(residenceBuildFormList);
    }

    /**
     * 查询某个住宅建筑形式的详细信息
     * @param buildFormId
     * @return
     */
    @RequestMapping(value = "/getResidentialBuildForm")
    @ResponseBody
    public NashResult getResidentialBuildForm(@RequestParam(value = "id") Integer buildFormId) {

        return NashResult.build(configService.retrieveResidentialBuildForm(buildFormId));
    }

    /**
     * 添加住宅建筑形式
     * @param buildFormRequest
     * @return
     */
    @RequestMapping(value = "/saveResidentialBuildForm", method = RequestMethod.POST)
    @ResponseBody
    public NashResult addResidentialBuildForm (@Validated(Second.class) ResidenceBuildFormRequest buildFormRequest) {

        ResidenceBuildForm buildForm = new ResidenceBuildForm();
        BeanUtils.copyProperties(buildFormRequest, buildForm);

        return NashResult.build(configService.addResidentialBuildForm(buildForm));
    }

    /**
     * 更新住宅建筑形式
     * @param buildFormRequest
     * @return
     */
    @RequestMapping(value = "/updateResidentialBuildForm", method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateResidentialBuildForm (@Validated(First.class) ResidenceBuildFormRequest buildFormRequest) {

        ResidenceBuildForm buildForm = new ResidenceBuildForm();
        BeanUtils.copyProperties(buildFormRequest, buildForm);

        return NashResult.build(configService.updateResidentialBuildForm(buildForm));
    }

    /**
     * 删除住宅建筑形式
     * @param buildFormId
     * @return
     */
    @RequestMapping(value = "/deleteResidentialBuildForm")
    public NashResult delResidentialBuildForm (@RequestParam("id") Integer buildFormId) {
        return NashResult.build(configService.delResidentialBuildForm(buildFormId));
    }


    /**************************************住宅建筑形式end****************************************************/
}
