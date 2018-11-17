package com.toutiao.officedict.apiimpl.controller;


import com.alibaba.fastjson.JSON;
import com.toutiao.officedict.api.chance.request.AreaRequest;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.domain.query.AreaQuery;
import com.toutiao.officedict.service.area.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 武守磊
 */
@RestController
@RequestMapping(value= "/area")
public class AreaServeController {

    private Logger logger = LoggerFactory.getLogger(AreaServeController.class);

    @Autowired
    private AreaService areaService;

    /**
     * 获取商圈列表
     * @param areaQuery
     * @return
     */
    @RequestMapping("/area-list")
    @ResponseBody
    public NashResult getAreaList(AreaQuery areaQuery){
//        User.getCurrent().getUserId()
        SerializableData serializableData = User.getCurrent().getSerializableData();
        areaQuery.setCurrentCityId(serializableData.getCityId());
        List<Area> areaList = areaService.getAreaList(areaQuery);
        return NashResult.build(areaList);

    }

    @RequestMapping("/area-listByDistrictId")
    @ResponseBody
    public NashResult listByDistrictId(AreaQuery areaQuery){
//        User.getCurrent().getUserId()
        List<Area> areaList = areaService.areaListByDistrictId(areaQuery);
        return NashResult.build(areaList);
    }

    /**
     * 根据商圈id删除城市
     * @param areaId
     * @return
     */
    @RequestMapping(value= "/del-area")
    @ResponseBody
    public NashResult delAreaById(@RequestParam("id") Integer areaId){
        NashResult nashResult = new NashResult();
        int delResult =  areaService.delArea(areaId);
        if(delResult>0){
            nashResult = NashResult.build(delResult);
        }else {
            nashResult = NashResult.Fail("false","删除区县之前，请先解除对应的区县绑定！",delResult);
        }
        return nashResult;
    }

    /**
     * 添加商圈
     * @param areaRequest
     * @return
     */
    @RequestMapping(value= "/add-area", method = RequestMethod.POST)
    @ResponseBody
    public NashResult addArea(@Validated(First.class)AreaRequest areaRequest){
        NashResult nashResult = new NashResult();
        Area area = new Area();
        BeanUtils.copyProperties(areaRequest, area);
        int  addResult = areaService.addArea(area);

        if(addResult>0){
            nashResult = NashResult.build(addResult);
        }else if(addResult==-1){
            nashResult = NashResult.Fail("false","您输入的商圈已经存在！",addResult);
        }
        return nashResult;
    }

    /**
     * 更新商圈
     * @param areaRequest
     * @return
     */
    @RequestMapping(value= "/update-area",method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateArea(@Validated(Second.class) AreaRequest areaRequest){
        Area area = new Area();
        BeanUtils.copyProperties(areaRequest, area);
        int updateResult = areaService.updateArea(area);
        return NashResult.build(updateResult);

    }

    /**
     * 根据商圈id查询城市详情
     * @param areaId
     * @return
     */
    @RequestMapping(value = "/find-area",consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public NashResult findCityDetails(@RequestBody Integer areaId){
        String result = "";
        logger.info("根据商圈d查询详情areaId:{}", areaId);
        if(areaId != null){
            Area area = areaService.findAreaById(areaId);
            result = JSON.toJSONString(area);
            logger.info("根据商圈id详情返回结果, result={}", result);
        }
        return NashResult.build(result);
    }



}
