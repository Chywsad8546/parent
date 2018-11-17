package com.toutiao.officedict.service.city;

import com.toutiao.officedict.dao.entity.officedict.Cities;
import com.toutiao.officedict.domain.query.CityQuery;

import java.util.List;

/**
 * 城市管理服务类
 *
 */
public interface CityService {

    /**
     * 获取城市列表
     * @return
     */
    List<Cities> getCityLists(CityQuery cityQuery);
    List<Cities> getlistCities();

    /**
     * 根据城市id删除城市
     * @param cityId
     * @return
     */
    int delCity(Integer cityId);

    /**
     * 添加城市
     * @param cities
     * @return
     */
    int addCity(Cities cities);

    /**
     * 更新城市
     * @param cities
     * @return
     */
    int updateCity(Cities cities);

    /**
     * 根据城市id查询详情
     * @param cityId
     * @return
     */
    Cities findCityById(Integer cityId);


}
