package com.toutiao.officedict.service.districtAreaConfig.impl;

import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.dao.entity.officedict.Cities;
import com.toutiao.officedict.dao.entity.officedict.District;
import com.toutiao.officedict.dao.entity.officedict.DistrictAreaConfig;
import com.toutiao.officedict.dao.mapper.officedict.AreaMapper;
import com.toutiao.officedict.dao.mapper.officedict.CitiesMapper;
import com.toutiao.officedict.dao.mapper.officedict.DistrictAreaConfigMapper;
import com.toutiao.officedict.dao.mapper.officedict.DistrictMapper;
import com.toutiao.officedict.domain.districtArea.*;
import com.toutiao.officedict.domain.query.CityQuery;
import com.toutiao.officedict.domain.query.DistrictAreaConfigQuery;
import com.toutiao.officedict.domain.query.DistrictQuery;
import com.toutiao.officedict.service.districtAreaConfig.DistrictAreaConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DistrictAreaConfigServiceImpl implements DistrictAreaConfigService {

    private Logger logger = LoggerFactory.getLogger(DistrictAreaConfigServiceImpl.class);

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private CitiesMapper citiesMapper;
    @Autowired
    private DistrictAreaConfigMapper districtAreaConfigMapper;



    @Override
    public List<AreaWithDistrictID> findArea(Integer Cityid,Integer housetype){
        return areaMapper.selectByCityIdAndHouseType(Cityid,housetype);
    }

    @Override
    public List<District> findDistrict(Integer cityid,Integer housetype){
        return districtMapper.selectDistrictListByCityIdAndHouseTye(cityid,housetype);
    }

    /**
     * 根据区县城市id和区县id查询区县关联商圈信息
     * @param districtAreaConfigQuery
     * @return
     */
    @Override
    public List<DistrictAreaDomain> findDistrictAreaConfig(DistrictAreaConfigQuery districtAreaConfigQuery) {

        List<DistrictAreaDomain> districtAreaDomainList = new ArrayList<>();


        DistrictQuery districtQuery = new DistrictQuery();
        districtQuery.setCityId(districtAreaConfigQuery.getCityId());
        List<District> districts = districtMapper.selectDistrictListByCityId(districtQuery);
        for (District district : districts){
            List<BindingArea> bindingAreas = new ArrayList<>();
            DistrictAreaDomain districtAreaDomain = new DistrictAreaDomain();
            //根据住宅类型区县id查询绑定商圈
            districtAreaConfigQuery.setDistrictId(district.getDistrictId());
            List<DistrictAreaConfig> list = districtAreaConfigMapper.selectConfigListByDisIdHouType(districtAreaConfigQuery);
            if(list.size() > 0){
                for(DistrictAreaConfig dac : list){
                    BindingArea bindingArea = new BindingArea();
                    Area area = areaMapper.selectByPrimaryKey(dac.getAreaId());
                    bindingArea.setAreaId(dac.getAreaId());
                    bindingArea.setAreaName(area.getAreaName());
                    bindingAreas.add(bindingArea);
                }
            }
            districtAreaDomain.setBindingAreas(bindingAreas);
            districtAreaDomain.setDistrictId(district.getDistrictId());
            districtAreaDomain.setDistrictName(district.getDistrictName());
            districtAreaDomainList.add(districtAreaDomain);
        }
        return districtAreaDomainList;

    }

    @Override
    public List<DistrictAreaConfig> getDistrictId(DistrictAreaConfigQuery districtAreaConfig) {
        List<DistrictAreaConfig> areaConfigs = districtAreaConfigMapper.selectByDistrictId(districtAreaConfig);
        return areaConfigs;
    }

    @Override
    public int bindingDistrictArea(List<Integer> areaIds, Integer districtId,Integer houseType) {
        int bindingResult = 0;
        DistrictAreaConfig districtAreaConfig = new DistrictAreaConfig();
        districtAreaConfig.setHouseType(houseType);
        districtAreaConfig.setDistrictId(districtId);

        for(Integer areaId : areaIds){
            districtAreaConfig.setAreaId(areaId);
            DistrictAreaConfig dac = districtAreaConfigMapper.selectCheckConfig(districtAreaConfig);
            if(null == dac){
                try {
                    bindingResult = districtAreaConfigMapper.insertSelective(districtAreaConfig);
                    if (bindingResult > 0){
                        logger.info("区县商圈绑定成功districtId:{},areaId:{},houseType:{}",districtAreaConfig.getDistrictId()
                                ,districtAreaConfig.getAreaId(),districtAreaConfig.getHouseType());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                bindingResult = -1;
                return bindingResult;
            }
        }
        return bindingResult;
    }

    /**
     * 解绑区县商圈
     * @param areaIds
     * @param districtId
     * @param houseType
     * @return
     */
    @Override
    public int unBundlingDistrictArea(List<Integer> areaIds, Integer districtId,Integer houseType) {
        int delResult = 0;
        DistrictAreaConfig districtAreaConfig = new DistrictAreaConfig();
        try {
            for (Integer areaId : areaIds){
                districtAreaConfig.setDistrictId(districtId);
                districtAreaConfig.setAreaId(areaId);
                districtAreaConfig.setHouseType(houseType);
                delResult = districtAreaConfigMapper.delDistrictAreaConfig(districtAreaConfig);
                if(delResult > 0){
                    logger.info("解绑区县商圈数据成功districtId:{},areaId:{}",districtAreaConfig.getDistrictId(),districtAreaConfig.getAreaId());
                }
            }
        }catch (Exception e){
            logger.info("解绑区县商圈数据失败districtId:{},areaId:{}",districtAreaConfig.getDistrictId(),districtAreaConfig.getAreaId());
            e.printStackTrace();
        }
        return delResult;
    }

    /**
     * 根据商圈拼音首字母查询未绑定商圈
     * @param districtAreaConfigQuery
     * @return
     */
    @Override
    public List<Area> unBundlingAreaByPinyin(DistrictAreaConfigQuery districtAreaConfigQuery) {

        List<Area> areas = areaMapper.selectUnboundAreaByPurposesAndId(districtAreaConfigQuery);
        return areas;
    }

    /**
     * 城市，区域，商圈级联数据
     * @return
     */
    @Override
    public CascadeControlDomain findDistrictCityAreaConfig(DistrictAreaConfigQuery districtAreaConfigQuery) {

        CascadeControlDomain cascadeControlDomain = new CascadeControlDomain();

        CityQuery cityQuery = new CityQuery();
        cityQuery.setCityId(districtAreaConfigQuery.getCityId());
        List<Cities> cities = citiesMapper.selectCityList(cityQuery);
        List<CityCascade> cityCascadeList = new ArrayList<>();
        for(Cities city : cities){
            CityCascade cityCascade = new CityCascade();
            cityCascade.setValue(city.getCityId());
            cityCascade.setText(city.getCityName());
            DistrictQuery districtQuery = new DistrictQuery();
            districtQuery.setCityId(city.getCityId());
            List<District> districtList = districtMapper.selectDistrictListByCityId(districtQuery);
            List<DistrictCascade> districts = new ArrayList<>();
            for(District district : districtList){
                DistrictCascade districtCascade = new DistrictCascade();
                districtCascade.setValue(district.getDistrictId());
                districtCascade.setText(district.getDistrictName());
                districts.add(districtCascade);
                districtAreaConfigQuery.setDistrictId(district.getDistrictId());
                List<DistrictAreaConfig> areas = districtAreaConfigMapper.selectConfigListByDisIdHouType(districtAreaConfigQuery);
                List<AreaCascade> areaCascadeList = new ArrayList<>();
                for(DistrictAreaConfig dac : areas){
                    AreaCascade areaCascade = new AreaCascade();
                    areaCascade.setValue(dac.getAreaId());
                    Area area = areaMapper.selectByPrimaryKey(dac.getAreaId());
                    areaCascade.setText(area.getAreaName());
                    areaCascadeList.add(areaCascade);
                }
                districtCascade.setList(areaCascadeList);
            }
            cityCascade.setList(districts);
            cityCascadeList.add(cityCascade);
        }
        cascadeControlDomain.setDatas(cityCascadeList);
        return cascadeControlDomain;
    }
}
