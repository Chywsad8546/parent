package com.toutiao.officedict.apiimpl.controller.newhouse;

import com.toutiao.officedict.apiimpl.controller.relation.RelationOfMaiTianController;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.DynamicInfo;
import com.toutiao.officedict.domain.query.ProjDynamicInfoQuery;
import com.toutiao.officedict.service.newhouse.ProjectDynamicInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 18710 on 2018/4/17.
 */
@RestController
@RequestMapping(value= "/dynamicInfo")
public class ProjectDynamicInfoController {
    private Logger logger = LoggerFactory.getLogger(RelationOfMaiTianController.class);
    @Autowired
    private ProjectDynamicInfoService projectDynamicInfoService;

    @RequestMapping(value = "/dynamicInfoList",method = RequestMethod.POST)
    @ResponseBody
    public NashResult getDynamicInfoList(ProjDynamicInfoQuery projDynamicInfoQuery){
//        User.getCurrent().getUserId()
        List<DynamicInfo> relaTionOfMaiTianQueryList = projectDynamicInfoService.queryDynamicInfo(projDynamicInfoQuery);
        return NashResult.build(relaTionOfMaiTianQueryList);
    }

    @RequestMapping(value = "/addDynamicInfo",method = RequestMethod.POST)
    @ResponseBody
    public NashResult addDynamicInfo(ProjDynamicInfoQuery projDynamicInfoQuery){
//        User.getCurrent().getUserId()
        Integer re = projectDynamicInfoService.addDynamicInfo(projDynamicInfoQuery);
        return NashResult.build(re);
    }

    @RequestMapping(value = "/removeDynamicInfo",method = RequestMethod.GET)
    @ResponseBody
    public NashResult removeDynamicInfo(Integer id){
//        User.getCurrent().getUserId()
        Integer re = projectDynamicInfoService.removeDynamicInfo(id);
        return NashResult.build(re);
    }

    @RequestMapping(value = "/oneDynamicInfo",method = RequestMethod.GET)
    @ResponseBody
    public NashResult getOneDynamicInfo(Integer id){
//        User.getCurrent().getUserId()
        DynamicInfo re = projectDynamicInfoService.selectOne(id);
        return NashResult.build(re);
    }

    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateById(ProjDynamicInfoQuery projDynamicInfoQuery){
//        User.getCurrent().getUserId()
        Integer re = projectDynamicInfoService.updateByIde(projDynamicInfoQuery);
        return NashResult.build(re);
    }
}
