package com.toutiao.officedict.service.newhouse.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.EsfHousing;
import com.toutiao.officedict.dao.entity.officedict.NewHouseHousing;
import com.toutiao.officedict.dao.mapper.officedict.NewHouseHousingMapper;
import com.toutiao.officedict.domain.query.HousingQuery;
import com.toutiao.officedict.service.newhouse.NewHouseHouingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WuShoulei on 2017/11/21
 */
@Service
public class NewHouseHouingServiceImpl implements NewHouseHouingService {

    @Autowired
    private NewHouseHousingMapper newHouseHousingMapper;

    @Override
    public List<NewHouseHousing> queryNewHouseHousingList(HousingQuery housingQuery) {

        PageHelper.startPage(housingQuery.getPageNum(), housingQuery.getPageSize());
        List<NewHouseHousing> newHouseHousingList = newHouseHousingMapper.queryNewHouseHousingList(housingQuery);

        return newHouseHousingList;
    }

    @Override
    public int addNewHouseHousing(NewHouseHousing newHouseHousing) {

        return newHouseHousingMapper.insertSelective(newHouseHousing);
    }

    @Override
    public int updateNewHouseHousing(NewHouseHousing newHouseHousing) {

        return newHouseHousingMapper.updateByPrimaryKeySelective(newHouseHousing);
    }

    @Override
    public int logicDelNewHouseHousing(NewHouseHousing newHouseHousing) {

        return newHouseHousingMapper.updateHousingDelStatus(newHouseHousing);
    }

    @Override
    public NewHouseHousing getNewHouseHousing(Integer houseId) {

        return newHouseHousingMapper.selectByPrimaryKey(houseId);
    }

}
