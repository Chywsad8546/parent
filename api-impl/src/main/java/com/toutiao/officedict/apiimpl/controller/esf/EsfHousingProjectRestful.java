package com.toutiao.officedict.apiimpl.controller.esf;

import com.toutiao.officedict.api.chance.request.HousingProjectRequest;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.apiimpl.controller.BaseController;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import com.toutiao.officedict.common.constant.IsApproveEnum;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.ProjectInfo;
import com.toutiao.officedict.domain.query.HousingProjectQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 二手房楼盘管理
 * @author WuShoulei on 2017/11/15
 */
@RestController
@RequestMapping("/esf")
public class EsfHousingProjectRestful extends BaseController {

    @Autowired
    private HousingProjectService housingProjectService;

    /**
     * 楼盘列表-二手房
     * @param query
     * @return
     */
    @RequestMapping(value= "/listHousingProject")
    @ResponseBody
    public NashResult housingProjectList(HousingProjectQuery query) {
        //二手房楼盘标志位
        query.setProjFlag(1);
        SerializableData serializableData = User.getCurrent().getSerializableData();
        query.setCurrentCityId(serializableData.getCityId());
        List<ProjectInfo> projInfoList = housingProjectService.listHousingProject(query);

        return NashResult.build(projInfoList);
    }

    /**
     * 添加/创建项目-二手房
     * @param housingProjectRequest
     * @return
     */
    @RequestMapping(value = "/saveHousingProject", method = RequestMethod.POST)
    @ResponseBody
    public NashResult createResidentialProject(@Validated({Second.class}) HousingProjectRequest housingProjectRequest,
                                               @ModelAttribute("user") User user) {

        housingProjectRequest.setCreatorId(user.getUserId());
        //二手房楼盘标志位
        housingProjectRequest.setNOrE((short)1);

        ProjectInfo projInfo = new ProjectInfo();

        SerializableData serializableData = User.getCurrent().getSerializableData();
        projInfo.setCityId(serializableData.getCityId());
        BeanUtils.copyProperties(housingProjectRequest, projInfo);

        return NashResult.build(housingProjectService.createProj(projInfo));
    }

    /**
     * 查询项目-二手房
     * @param newcode
     * @return
     */
    @RequestMapping(value = "/getHousingProject", method = RequestMethod.GET)
    public NashResult retrieveResidentialProject(@RequestParam(value = "newcode") Integer newcode) {

        return NashResult.build(housingProjectService.retrieveProj(newcode));
    }


    /**
     * 更新项目-二手房
     * @param housingProjectRequest
     * @return
     */
    @RequestMapping(value = "/updateHousingProject", method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateResidentialProject(@Validated({First.class}) HousingProjectRequest housingProjectRequest,
                                               @ModelAttribute("user") User user) {
        housingProjectRequest.setUpdaterId(user.getUserId());

        ProjectInfo projInfo = new ProjectInfo();
        BeanUtils.copyProperties(housingProjectRequest, projInfo);

        return NashResult.build(housingProjectService.updateProj(projInfo));
    }

    /**
     * 删除项目-二手房
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
    public NashResult releaseResidentialProject(@Validated({First.class}) HousingProjectRequest housingProjectRequest,
                                                @ModelAttribute("user") User user) {

        housingProjectRequest.setUpdaterId(user.getUserId());

        ProjectInfo projInfo = new ProjectInfo();
        BeanUtils.copyProperties(housingProjectRequest, projInfo);
        int isApprove = IsApproveEnum.Approve2.getValue();
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
    public NashResult cancelReleaseResidentialProject(@Validated({First.class}) HousingProjectRequest housingProjectRequest,
                                                      @ModelAttribute("user") User user) {

        housingProjectRequest.setUpdaterId(user.getUserId());

        ProjectInfo projInfo = new ProjectInfo();

        BeanUtils.copyProperties(housingProjectRequest, projInfo);
        int isApprove = IsApproveEnum.Approve1.getValue();
        projInfo.setIsApprove((short) isApprove);
        return NashResult.build(housingProjectService.updateProj(projInfo));
    }

}
