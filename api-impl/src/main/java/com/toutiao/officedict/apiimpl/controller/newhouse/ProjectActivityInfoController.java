package com.toutiao.officedict.apiimpl.controller.newhouse;

import com.toutiao.officedict.apiimpl.controller.relation.RelationOfMaiTianController;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.ActivityInfo;
import com.toutiao.officedict.dao.entity.officedict.DynamicInfo;
import com.toutiao.officedict.dao.entity.officedict.ProjectInfo;
import com.toutiao.officedict.domain.query.ActivityInfoQuery;
import com.toutiao.officedict.domain.query.ProjDynamicInfoQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectService;
import com.toutiao.officedict.service.newhouse.ProjActivityInfoService;
import com.toutiao.officedict.service.newhouse.ProjectDynamicInfoService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by 18710 on 2018/9/17.
 */
@RestController
@RequestMapping(value= "/activityInfo")
public class ProjectActivityInfoController
{
    private Logger logger = LoggerFactory.getLogger(RelationOfMaiTianController.class);
    @Autowired
    private ProjActivityInfoService projActivityInfoService;

    @Autowired
    private HousingProjectService housingProjectService;

  /*  @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }*/

    @RequestMapping(value = "/activityList",method = RequestMethod.POST)
    @ResponseBody
    public NashResult getActivityList(ActivityInfoQuery activityInfoQuery){
//        User.getCurrent().getUserId()
        List<ActivityInfo> activityInfoList = projActivityInfoService.queryActivityInfo(activityInfoQuery);
        return NashResult.build(activityInfoList);
    }

    @RequestMapping(value = "/addActivity",method = RequestMethod.POST)
    @ResponseBody
    public NashResult addActivity(ActivityInfoQuery activityInfoQuery){
//        User.getCurrent().getUserId()
        activityInfoQuery.setCreateTime(DateTime.now().toDate());
        Integer activityInfo = projActivityInfoService.addActivity(activityInfoQuery);

        if (activityInfo>0){
            ProjectInfo projectInfo =new ProjectInfo();
            projectInfo.setNewcode(activityInfoQuery.getNewcode());
            Integer res=housingProjectService.updateIsAcitivity(projectInfo);
        }
        return NashResult.build(activityInfo);
    }

    @RequestMapping(value = "/oneActivityInfo",method = RequestMethod.POST)
    @ResponseBody
    public NashResult oneActivityInfo(Integer id){
//        User.getCurrent().getUserId()
        ActivityInfo activityInfo = projActivityInfoService.oneActivityInfo(id);
        return NashResult.build(activityInfo);
    }

    @RequestMapping(value = "/updateById",method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateById(ActivityInfoQuery activityInfoQuery){
//        User.getCurrent().getUserId()
        activityInfoQuery.setUpdateTime(DateTime.now().toDate());
        Integer activityInfo = projActivityInfoService.updateById(activityInfoQuery);

        if (activityInfo>0){
            ProjectInfo projectInfo =new ProjectInfo();
            projectInfo.setNewcode(activityInfoQuery.getNewcode());
            Integer res= housingProjectService.updateIsAcitivity(projectInfo);
        }
        return NashResult.build(activityInfo);
    }

    @RequestMapping(value = "/removeActivityInfo",method = RequestMethod.POST)
    @ResponseBody
    public NashResult removeActivityInfo(ActivityInfoQuery activityInfoQuery){
//        User.getCurrent().getUserId()
        Integer activityInfo = projActivityInfoService.removeActivityInfo(activityInfoQuery.getId());
        if (activityInfo>0){
            ProjectInfo projectInfo =new ProjectInfo();
            projectInfo.setNewcode(activityInfoQuery.getNewcode());
            Integer res= housingProjectService.updateIsAcitivity(projectInfo);
        }
        return NashResult.build(activityInfo);
    }
}
