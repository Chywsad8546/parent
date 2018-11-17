package com.toutiao.officedict.apiimpl.controller;


import com.alibaba.fastjson.JSON;
import com.toutiao.officedict.api.chance.request.SubwayRequest;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.SubwayLine;
import com.toutiao.officedict.dao.entity.officedict.SubwayStation;
import com.toutiao.officedict.domain.query.SubwayQuery;
import com.toutiao.officedict.service.subway.SubwayStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/subwayStation")
public class SubwayStationServeController {


    private Logger logger = LoggerFactory.getLogger(SubwayStationServeController.class);

    @Autowired
    private SubwayStationService subwayStationService;

    /**
     * 根据地铁线id获取地铁站列表
     * @param subwayQuery
     * @return
     */
    @RequestMapping("/subwaystation-list")
    @ResponseBody
    public NashResult getSubwayStationList(SubwayQuery subwayQuery){

        List<SubwayStation> subwayStationList =subwayStationService.getSubwayStationList(subwayQuery);
        return NashResult.build(subwayStationList);

    }


    /**
     * 添加地铁站信息
     * @param subwayRequest
     * @return
     */
    @RequestMapping(value= "/add-subwaystation", consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public NashResult addSubwayStation(@Validated(First.class) @RequestBody SubwayRequest subwayRequest){
        NashResult nashResult = new NashResult();
        SubwayStation subwayStation = new SubwayStation();
        BeanUtils.copyProperties(subwayRequest, subwayStation);
        int  addResult = subwayStationService.addSubwayStation(subwayStation);
        if(addResult>0){
            nashResult = NashResult.build(addResult);
        }else if(addResult==-1){
            nashResult = NashResult.Fail("false","您输入的地铁站已经存在！",addResult);
        }
        return nashResult;
    }


    /**
     * 根据line id删除地铁线
     * @param stationId
     * @return
     */
    @RequestMapping(value= "/del-subwaystations")
    @ResponseBody
    public NashResult delSubwayStationById(@RequestParam("id") Integer stationId){

        int delResult =  subwayStationService.delSubwayStation(stationId);

        return NashResult.build(delResult);
    }

    /**
     *
     * 修改地铁站信息
     * @param subwayRequest
     * @return
     */
    @RequestMapping(value= "/update-subwaystation", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateSubwayStation(@RequestBody SubwayRequest subwayRequest){

        SubwayStation subwayStation = new SubwayStation();
        BeanUtils.copyProperties(subwayRequest, subwayStation);
        int updateResult = subwayStationService.updateSubwayStation(subwayStation);
        return NashResult.build(updateResult);

    }

    /**
     * 根据地铁线id查询地铁线详情
     * @param stationId
     * @return
     */
    @RequestMapping(value = "/find-subwaystations",consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public NashResult findSubwayStationsDetails(@RequestBody Integer stationId){
        String result = "";
        logger.info("根据地铁站id查询详情stationId:{}", stationId);
        if(stationId != null){
            SubwayStation subwayStation = subwayStationService.findSubwayStationById(stationId);
            result = JSON.toJSONString(subwayStation);
            logger.info("根据地铁站id详情返回结果, result={}", result);
        }
        return NashResult.build(result);
    }
}
