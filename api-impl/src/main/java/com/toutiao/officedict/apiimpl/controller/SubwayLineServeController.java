package com.toutiao.officedict.apiimpl.controller;


import com.alibaba.fastjson.JSON;
import com.toutiao.officedict.api.chance.request.SubwayRequest;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.SubwayLine;
import com.toutiao.officedict.domain.query.SubwayQuery;
import com.toutiao.officedict.service.subway.SubwayLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/subwayLine")
public class SubwayLineServeController {

    private Logger logger = LoggerFactory.getLogger(SubwayLineServeController.class);

    @Autowired
    private SubwayLineService subwayLineService;

    /**
     * 获取地铁线列表
     * @param subwayQuery
     * @return
     */
    @RequestMapping("/subwayLine-list")
    @ResponseBody
    public NashResult getSubwayLineList(SubwayQuery subwayQuery){

        List<SubwayLine> subwayLineList =subwayLineService.getSubwayLineList(subwayQuery);
        return NashResult.build(subwayLineList);

    }

    /**
     * 根据line id删除地铁线
     * @param lineId
     * @return
     */
    @RequestMapping(value= "/del-subwayLine")
    @ResponseBody
    public NashResult delAreaById(@RequestParam("id") Integer lineId){

        int delResult =  subwayLineService.delSubway(lineId);

        return NashResult.build(delResult);
    }

    /**
     * 添加地铁线
     * @param subwayRequest
     * @return
     */
    @RequestMapping(value= "/add-subwayLine", consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public NashResult addSubwayLine(@RequestBody SubwayRequest subwayRequest){
        NashResult nashResult = new NashResult();
        SubwayLine subwayLine = new SubwayLine();
        BeanUtils.copyProperties(subwayRequest, subwayLine);
        int  addResult = subwayLineService.addSubway(subwayLine);
        if(addResult>0){
            nashResult = NashResult.build(addResult);
        }else if(addResult==-1){
            nashResult = NashResult.Fail("false","您输入的地铁线已经存在！",addResult);
        }
        return nashResult;
    }

    /**
     * 更新地铁线
     * @param subwayRequest
     * @return
     */
    @RequestMapping(value= "/update-subwayLine", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateSubwayLine(@RequestBody SubwayRequest subwayRequest){
        SubwayLine subwayLine = new SubwayLine();
        BeanUtils.copyProperties(subwayRequest, subwayLine);
        int updateResult = subwayLineService.updateSubwayLine(subwayLine);
        return NashResult.build(updateResult);
    }

    /**
     * 根据地铁线id查询地铁线详情
     * @param lineId
     * @return
     */
    @RequestMapping(value = "/find-subwayLine",consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public NashResult findSubwayLineDetails(@RequestBody Integer lineId){
        String result = "";
        logger.info("根据地铁线id查询详情lineId:{}", lineId);
        if(lineId != null){
            SubwayLine subwayLine = subwayLineService.findSubwayLineById(lineId);
            result = JSON.toJSONString(subwayLine);
            logger.info("根据地铁线id详情返回结果, result={}", result);
        }
        return NashResult.build(result);
    }
}
