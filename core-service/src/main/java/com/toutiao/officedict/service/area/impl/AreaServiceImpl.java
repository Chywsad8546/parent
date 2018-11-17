package com.toutiao.officedict.service.area.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.dao.entity.officedict.DistrictAreaConfig;
import com.toutiao.officedict.dao.mapper.officedict.AreaMapper;
import com.toutiao.officedict.dao.mapper.officedict.DistrictAreaConfigMapper;
import com.toutiao.officedict.domain.query.AreaQuery;
import com.toutiao.officedict.domain.query.DistrictAreaConfigQuery;
import com.toutiao.officedict.service.area.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AreaServiceImpl implements AreaService{

    private Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private DistrictAreaConfigMapper districtAreaConfigMapper;


    @Override
    public List<Area> getAreaList(AreaQuery areaQuery) {
        PageHelper.startPage(areaQuery.getPageNum(), areaQuery.getPageSize());
        List<Area> areaList = new ArrayList<>();
        try {
            areaList = areaMapper.selectAreaList(areaQuery);
            logger.info("查询商圈列表cityList:{}",areaList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return areaList;
    }

    @Override
    public int delArea(Integer areaId) {
        int delResult = 0;
        DistrictAreaConfigQuery districtAreaConfigQuery = new DistrictAreaConfigQuery();
        districtAreaConfigQuery.setAreaId(areaId);
        List<DistrictAreaConfig> list = districtAreaConfigMapper.selectByAreaId(districtAreaConfigQuery);

        if(list.size() > 0){
            logger.info("该商圈已与区域绑定，不能删除areaId:{}",areaId);
            delResult = -1;
        } else {
            delResult = areaMapper.deleteByPrimaryKey(areaId);
        }
        return delResult;
    }

    @Override
    public int addArea(Area area) {
        int addResult = 0;
        Area areas = areaMapper.selectByAreaName(area.getAreaName());
        if(null == areas){
            addResult = areaMapper.insertSelective(area);
            if(addResult > 0){
                logger.info("插入商圈数据成功area:{}",area);
                return area.getAreaId();
            }
        } else {
            logger.info("商圈已经存在area:{}", JSON.toJSONString(area));
            addResult = -1;
        }
        return addResult;
    }

    @Override
    public int updateArea(Area area) {
        int updateResult = 0;
        try{
            updateResult = areaMapper.updateByPrimaryKeySelective(area);
            if (updateResult > 0){
                logger.info("更新商圈数据成功city:{}",area);
            }
        }catch (Exception e){
            logger.info("更新商圈数据失败city:{}",area);
            e.printStackTrace();
        }finally {

            return updateResult;
        }
    }

    @Override
    public Area findAreaById(Integer areaId) {
        Area area = areaMapper.selectByPrimaryKey(areaId);
        return area;
    }

    @Override
    public List<Area> findAreaByDistrictId(DistrictAreaConfig districtAreaConfig) {

        List<Area> areaList = new ArrayList<>();
        List<DistrictAreaConfig> dac = districtAreaConfigMapper.selectAreaByTypeAndIds(districtAreaConfig);
        for(DistrictAreaConfig list : dac){
            Area area = areaMapper.selectByPrimaryKey(list.getAreaId());
            areaList.add(area);
        }
        return areaList;
    }

    @Override
    public List<Area> areaListByDistrictId(AreaQuery areaQuery) {
        PageHelper.startPage(areaQuery.getPageNum(), areaQuery.getPageSize());
        List<Area> areaList = new ArrayList<>();
        areaList = areaMapper.selectByDistrictID(areaQuery);
        return areaList;
    }
}
