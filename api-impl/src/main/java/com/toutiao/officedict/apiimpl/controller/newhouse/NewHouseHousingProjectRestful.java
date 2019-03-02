package com.toutiao.officedict.apiimpl.controller.newhouse;

import com.toutiao.officedict.api.chance.request.DynamicInfoRequest;
import com.toutiao.officedict.api.chance.request.HousingProjectRequest;
import com.toutiao.officedict.api.chance.request.SalesLicenseInfoRequest;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.apiimpl.controller.BaseController;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import com.toutiao.officedict.common.constant.IsApproveEnum;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.ProjectInfo;
import com.toutiao.officedict.domain.newHouse.SalesDynamicInfo;
import com.toutiao.officedict.domain.newHouse.SalesLicenseInfo;
import com.toutiao.officedict.domain.query.HousingProjectQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 新房楼盘管理
 * @author WuShoulei on 2017/11/15
 */
@RestController
@RequestMapping("/newHouse")
public class NewHouseHousingProjectRestful extends BaseController {

    @Autowired
    private HousingProjectService housingProjectService;

    private static final Logger logger = LoggerFactory.getLogger(NewHouseHousingProjectRestful.class);

    /**
     * 楼盘列表-新房
     * @param query
     * @return
     */
    @RequestMapping(value= "/listHousingProject")
    @ResponseBody
    public NashResult listNewHouseHousingProject(HousingProjectQuery query) {

        //新房楼盘标志位
        query.setProjFlag(0);
        SerializableData serializableData = User.getCurrent().getSerializableData();
        query.setCurrentCityId(serializableData.getCityId());
        logger.error(DateTime.now().toString());
        List<ProjectInfo> projInfoList = housingProjectService.listHousingProject(query);
        logger.error(DateTime.now().toString());
        return NashResult.build(projInfoList);
    }

    /**
     * 添加/创建项目-新房
     * @param housingProjectRequest
     * @return
     */
    @RequestMapping(value = "/saveHousingProject", method = RequestMethod.POST)
    @ResponseBody
    public NashResult createResidentialProject(@Validated({Second.class}) HousingProjectRequest housingProjectRequest,
                                               @ModelAttribute("user") User user) {

        housingProjectRequest.setCreatorId(user.getUserId());
        //新房楼盘标志位
        housingProjectRequest.setNOrE((short)0);

        ProjectInfo projInfo = new ProjectInfo();
        BeanUtils.copyProperties(housingProjectRequest, projInfo);

        return NashResult.build(housingProjectService.createProj(projInfo));
    }

    /**
     * 查询项目-新房
     * @param newcode
     * @return
     */
    @RequestMapping(value = "/getHousingProject", method = RequestMethod.GET)
    public NashResult retrieveResidentialProject(@RequestParam(value = "newcode") Integer newcode) {

        return NashResult.build(housingProjectService.retrieveProj(newcode));
    }


    /**
     * 更新项目-新房
     * @param housingProjectRequest
     * @return
     */
    @RequestMapping(value = "/updateHousingProject", method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateResidentialProject(@Validated({First.class}) HousingProjectRequest housingProjectRequest,
                                               @ModelAttribute("user") User user) {

        //添加更新人信息
        housingProjectRequest.setUpdaterId(user.getUserId());

        ProjectInfo projInfo = new ProjectInfo();
        BeanUtils.copyProperties(housingProjectRequest, projInfo);

        return NashResult.build(housingProjectService.updateProj(projInfo));
    }

    /**
     * 删除项目-新房
     * @param housingProjectRequest
     * @return
     */
    @RequestMapping(value = "/deleteHousingProject", method = RequestMethod.POST)
    @ResponseBody
    public NashResult delResidentialProject(@Validated({Third.class}) HousingProjectRequest housingProjectRequest,
                                            @ModelAttribute("user") User user) {

        housingProjectRequest.setUpdaterId(user.getUserId());

        ProjectInfo projInfo = new ProjectInfo();
        BeanUtils.copyProperties(housingProjectRequest, projInfo);

        return NashResult.build(housingProjectService.logicDelProj(projInfo));
    }

    /**
     * 发布项目-新房
     * @param housingProjectRequest
     * @return
     */
    @RequestMapping(value = "/releaseHousingProject", method = RequestMethod.POST)
    @ResponseBody
    public NashResult releaseResidentialProject(@Validated({First.class}) HousingProjectRequest housingProjectRequest) {
        int isApprove = IsApproveEnum.Approve2.getValue();
        ProjectInfo projInfo = new ProjectInfo();
        BeanUtils.copyProperties(housingProjectRequest, projInfo);
        projInfo.setIsApprove((short) isApprove);
        return NashResult.build(housingProjectService.updateProj(projInfo));
    }

    /**
     * 取消发布项目-新房
     * @param housingProjectRequest
     * @return
     */
    @RequestMapping(value = "/cancelReleaseHousingProject", method = RequestMethod.POST)
    @ResponseBody
    public NashResult cancelReleaseResidentialProject(@Validated({First.class}) HousingProjectRequest housingProjectRequest) {
        int isApprove = IsApproveEnum.Approve1.getValue();
        ProjectInfo projInfo = new ProjectInfo();
        BeanUtils.copyProperties(housingProjectRequest, projInfo);
        projInfo.setIsApprove((short) isApprove);
        return NashResult.build(housingProjectService.updateProj(projInfo));
    }

    /**
     * 获取某个楼盘的预售证列表
     * @param newcode
     * @return
     */
    @RequestMapping(value = "/listSalesLicenseInfo")
    @ResponseBody
    public NashResult listSalesLicenseInfo(@RequestParam("newcode") Integer newcode) {

        List<SalesLicenseInfo> salesLicenseInfoList = housingProjectService.listSalesLicenseInfo(newcode);

        if (null == salesLicenseInfoList) {
            salesLicenseInfoList = new ArrayList<>();
        }

        return NashResult.build(salesLicenseInfoList);
    }

    /**
     * 保存预售许可证
     * @return
     */
    @RequestMapping(value = "/saveSalesLicense", method = RequestMethod.POST)
    @ResponseBody
    public NashResult saveSalesLicense(@RequestBody List<SalesLicenseInfoRequest> salesLicenseInfoRequestList) {


        List<SalesLicenseInfo> salesLicenseInfos = new ArrayList<>();

        ProjectInfo projectInfo = new ProjectInfo();
        for (SalesLicenseInfoRequest infoRequest:salesLicenseInfoRequestList) {

            projectInfo.setNewcode(infoRequest.getNewcode());

            if (null != infoRequest.getLicenseName() && null != infoRequest.getBuildInfo()) {
                SalesLicenseInfo salesLicenseInfo = new SalesLicenseInfo();
                BeanUtils.copyProperties(infoRequest, salesLicenseInfo);
                salesLicenseInfos.add(salesLicenseInfo);
            }
        }

        //更新预售证信息
        projectInfo.setSalesLicenseInfo(salesLicenseInfos);

        return NashResult.build(housingProjectService.updateProj(projectInfo));
    }

    /**
     * 获取某个楼盘的楼盘动态列表
     * @param newcode
     * @return
     */
    @RequestMapping(value = "/listSalesDynamicInfo")
    @ResponseBody
    public NashResult listSalesDynamicInfo(@RequestParam("newcode") Integer newcode) {

        List<SalesDynamicInfo> salesDynamicInfoList = housingProjectService.listSalesDynamicInfo(newcode);

        if (null == salesDynamicInfoList) {
            salesDynamicInfoList = new ArrayList<>();
        }

        return NashResult.build(salesDynamicInfoList);
    }

    /**
     * 保存楼盘动态信息
     * @return
     */
    @RequestMapping(value = "/saveDynamicInfo", method = RequestMethod.POST)
    @ResponseBody
    public NashResult saveDynamicInfo(@RequestBody List<DynamicInfoRequest> dynamicInfoRequestList) {

        List<SalesDynamicInfo> salesDynamicInfoList = new ArrayList<>();

        ProjectInfo projectInfo = new ProjectInfo();

        for (DynamicInfoRequest infoRequest:dynamicInfoRequestList) {

            projectInfo.setNewcode(infoRequest.getNewcode());

            if (null != infoRequest.getTitle() && null != infoRequest.getDetail()) {
                SalesDynamicInfo dynamicInfo = new SalesDynamicInfo();
                BeanUtils.copyProperties(infoRequest, dynamicInfo);
                salesDynamicInfoList.add(dynamicInfo);
            }
        }

        //更新预售证信息
        projectInfo.setDynamicInfo(salesDynamicInfoList);

        return NashResult.build(housingProjectService.updateProj(projectInfo));
    }

    /**
     * 是否开启楼盘活动
     * @param projectInfo
     * @return
     */
    @RequestMapping(value = "/updateIsAcitivity")
    @ResponseBody
    public NashResult updateIsAcitivity(ProjectInfo projectInfo) {

        Integer res = housingProjectService.updateIsAcitivity(projectInfo);

        return NashResult.build(res);
    }

}
