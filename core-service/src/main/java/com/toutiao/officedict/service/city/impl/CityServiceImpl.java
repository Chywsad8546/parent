package com.toutiao.officedict.service.city.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.common.util.Pinyin4jUtil;
import com.toutiao.officedict.dao.entity.officedict.Cities;
import com.toutiao.officedict.dao.mapper.officedict.CitiesMapper;
import com.toutiao.officedict.domain.query.CityQuery;
import com.toutiao.officedict.service.city.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class CityServiceImpl implements CityService {

    private Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CitiesMapper citiesMapper;

    /**
     * 获取房源列表
     * @param cityQuery
     * @return
     */
    @Override
    public List<Cities> getCityLists(CityQuery cityQuery) {

        PageHelper.startPage(cityQuery.getPageNum(), cityQuery.getPageSize());
        List<Cities> cityList = new ArrayList<>();
        try {
            cityList = citiesMapper.selectCityList(cityQuery);
            logger.info("查询城市列表cityList:{}",cityList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cityList;
    }

    @Override
    public List<Cities> getlistCities() {
        List<Cities> cityList = new ArrayList<>();
        try {
            cityList = citiesMapper.selectListCities();
            logger.info("查询城市列表cityList:{}",cityList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return cityList;
    }


    /**
     * 根据城市id删除城市
     * @param cityId
     */
    @Override
    public int delCity(Integer cityId) {
        int delResult = 0;
        try {
            delResult = citiesMapper.deleteByPrimaryKey(cityId);
            if(delResult > 0){
                logger.info("删除城市数据成功cityId:{}",cityId);
            }
        }catch (Exception e){
            logger.info("删除城市数据失败cityId:{}",cityId);
            e.printStackTrace();
        }finally {
            return delResult;
        }

    }

    /**
     * 添加城市
     * @param cities
     * @return
     */
    @Override
    public int addCity(Cities cities) {
        int addResult = 0;
        Cities city = citiesMapper.selectByCityName(cities.getCityName());
        if(null == city){
            addResult = citiesMapper.insertSelective(cities);
            if(addResult > 0){
                logger.info("插入城市数据成功city:{}",JSON.toJSONString(cities));
            }
        }else{
            logger.info("城市已经存在city:{}", JSON.toJSONString(cities));
            addResult = -1;
        }
        return addResult;
    }

    /**
     * 更新城市
     * @param cities
     * @return
     */
    @Override
    public int updateCity(Cities cities) {
        int updateResult = 0;
        try{
            updateResult = citiesMapper.updateByPrimaryKeySelective(cities);
            if (updateResult > 0){
                logger.info("更新城市数据成功city:{}",cities);
            }
        }catch (Exception e){
            logger.info("更新城市数据失败city:{}",cities);
            e.printStackTrace();
        }finally {

            return updateResult;
        }
    }

    /**
     * 根据id查询城市详情
     * @param cityId
     * @return
     */
    @Override
    public Cities findCityById(Integer cityId) {

        Cities cities = citiesMapper.selectByPrimaryKey(cityId);

        return cities;
    }
}
