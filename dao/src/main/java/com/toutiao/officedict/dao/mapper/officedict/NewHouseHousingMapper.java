package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.NewHouseHousing;
import com.toutiao.officedict.domain.query.HousingQuery;

import java.util.List;

public interface NewHouseHousingMapper extends BaseDao {

    int deleteByPrimaryKey(Integer houseId);

    int insert(NewHouseHousing record);

    int insertSelective(NewHouseHousing record);

    NewHouseHousing selectByPrimaryKey(Integer houseId);

    int updateByPrimaryKeySelective(NewHouseHousing record);

    int updateByPrimaryKey(NewHouseHousing record);

    /**
     * 获取新房房源列表
     * @return
     */
    List<NewHouseHousing> queryNewHouseHousingList(HousingQuery housingQuery);

    /**
     * 删除房源-逻辑删除
     * @param newHouseHousing
     * @return
     */
    int updateHousingDelStatus(NewHouseHousing newHouseHousing);
}