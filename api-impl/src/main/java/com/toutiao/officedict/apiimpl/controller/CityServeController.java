package com.toutiao.officedict.apiimpl.controller;


import com.alibaba.fastjson.JSON;
import com.toutiao.officedict.api.chance.request.CitiesRequest;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.Cities;
import com.toutiao.officedict.domain.query.CityQuery;
import com.toutiao.officedict.service.city.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/city")
public class CityServeController {

    private Logger logger = LoggerFactory.getLogger(CityServeController.class);

    @Autowired
    private CityService cityService;

    /**
     * 获取城市列表
     * @param cityQuery
     * @return
     */
    @RequestMapping("/city-list")
    @ResponseBody
    public NashResult getCityList(CityQuery cityQuery){
        List<Cities> cityList = cityService.getCityLists(cityQuery);
        return NashResult.build(cityList);
    }

    /**
     * 根据城市id删除城市
     * @param cityId
     * @return
     */
    @RequestMapping(value= "/del-city")
    @ResponseBody
    public NashResult delCityById(@RequestParam("id") Integer cityId){
        int delResult =  cityService.delCity(cityId);
        return NashResult.build(delResult);
    }

    /**
     * 添加城市
     * @param citiesRequest
     * @return
     */
    @RequestMapping(value= "/add-city",method = RequestMethod.POST)
    @ResponseBody
    public NashResult addCity(@Validated(Second.class)  CitiesRequest citiesRequest){
        int  addResult = 0;
        NashResult nashResult = new NashResult();
        Cities cities = new Cities();
        BeanUtils.copyProperties(citiesRequest, cities);
        addResult = cityService.addCity(cities);
        if(addResult>0){
            nashResult = NashResult.build(addResult);
        }else if(addResult==-1){
            nashResult = NashResult.Fail("false","您输入的城市已经存在！",addResult);
        }
        return nashResult;
    }

    /**
     * 更新城市
     * @param citiesRequest
     * @return
     */
    @RequestMapping(value= "/update-city", method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateCity(@Validated(First.class) CitiesRequest citiesRequest){

        Cities cities = new Cities();
        BeanUtils.copyProperties(citiesRequest, cities);
        int updateResult = cityService.updateCity(cities);
        System.out.println(NashResult.build(updateResult));
        return NashResult.build(updateResult);

    }

    /**
     * 根据城市id查询城市详情
     * @param cityId
     * @return
     */
    @RequestMapping(value = "/find-city",consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public NashResult findCityDetails(@RequestBody Integer cityId){
        String result = "";
        logger.info("根据城市d查询详情cityId:{}", cityId);
        if(cityId != null){
            Cities cities = cityService.findCityById(cityId);
            result = JSON.toJSONString(cities);
            logger.info("根据城市id详情返回结果, result={}", result);
        }
        return NashResult.build(result);
    }

    @RequestMapping("/listCities")
    @ResponseBody
    public NashResult getlistCities(){
        List<Cities> cityList = cityService.getlistCities();
        return NashResult.build(cityList);
    }
}
