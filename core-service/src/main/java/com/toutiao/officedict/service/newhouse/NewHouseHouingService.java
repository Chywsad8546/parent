package com.toutiao.officedict.service.newhouse;

import com.toutiao.officedict.dao.entity.officedict.NewHouseHousing;
import com.toutiao.officedict.domain.query.HousingQuery;

import java.util.List;

/**
 * @author WuShoulei on 2017/11/21
 */
public interface NewHouseHouingService {

    /**
     * 房源列表-新房
     * @return
     */
    List<NewHouseHousing> queryNewHouseHousingList(HousingQuery housingQuery);

    /**
     * 添加房源-新房
     * @param newHouseHousing
     * @return
     */
    int addNewHouseHousing(NewHouseHousing newHouseHousing);

    /**
     * 更新房源-新房
     * @param newHouseHousing
     * @return
     */
    int updateNewHouseHousing(NewHouseHousing newHouseHousing);

    /**
     * 删除房源-新房
     * @param houseId
     * @return
     */
//  int delNewHouseHousing(Integer houseId);


    /**
     * 删除房源-逻辑删除
     * @param newHouseHousing
     * @return
     */
    int logicDelNewHouseHousing(NewHouseHousing newHouseHousing);

    /**
     * 获取新房房源信息
     * @param houseId
     * @return
     */
    NewHouseHousing getNewHouseHousing(Integer houseId);
}
