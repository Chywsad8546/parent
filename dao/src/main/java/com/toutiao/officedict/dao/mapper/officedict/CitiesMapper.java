package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.Cities;
import com.toutiao.officedict.domain.query.CityQuery;

import java.util.List;

public interface CitiesMapper extends BaseDao {
    int deleteByPrimaryKey(Integer cityId);

    int insert(Cities record);

    int insertSelective(Cities record);

    Cities selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(Cities record);

    int updateByPrimaryKey(Cities record);

    /**
     * 城市列表
     */
    List<Cities> selectCityList(CityQuery cityQuery);

    /**
     * 城市列表
     */
    List<Cities> selectListCities();

    /**
     * 根据城市名称查询城市信息
     * @param cityName
     * @return
     */
    Cities selectByCityName(String cityName);
}