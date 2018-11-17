package com.toutiao.officedict.service.subway.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.SubwayStation;
import com.toutiao.officedict.dao.mapper.officedict.SubwayStationMapper;
import com.toutiao.officedict.domain.query.SubwayQuery;
import com.toutiao.officedict.service.subway.SubwayStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubwayStationServiceImpl implements SubwayStationService{

    private Logger logger = LoggerFactory.getLogger(SubwayStationServiceImpl.class);

    @Autowired
    private SubwayStationMapper subwayStationMapper;


    /**
     * 查询line id查询地铁站
     * @param subwayQuery
     * @return
     */
    @Override
    public List<SubwayStation> getSubwayStationList(SubwayQuery subwayQuery) {
        PageHelper.startPage(subwayQuery.getPageNum(), subwayQuery.getPageSize());
        List<SubwayStation> list = new ArrayList<>();
        try {
            list = subwayStationMapper.selectSubwayStationList(subwayQuery);
            logger.info("查询地铁线列表subwayLineList:{}",list);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 修改地铁站
     * @param subwayStation
     * @return
     */
    @Override
    public int updateSubwayStation(SubwayStation subwayStation) {
        int updateResult = 0;
        try{
            updateResult = subwayStationMapper.updateByPrimaryKeySelective(subwayStation);
            if (updateResult > 0){
                logger.info("更新地铁站数据成功subwayStation:{}",subwayStation);
            }
        }catch (Exception e){
            logger.info("更新地铁站数据失败subwayStation:{}",subwayStation);
            e.printStackTrace();
        }finally {

            return updateResult;
        }
    }

    /**
     * 新增地铁站信息
     * @param subwayStation
     * @return
     */
    @Override
    public int addSubwayStation(SubwayStation subwayStation) {
        int addResult = 0;
        SubwayStation sS = subwayStationMapper.selectBySubwayStationName(subwayStation.getStationName());
        if(null == sS){
            addResult = subwayStationMapper.insertSelective(subwayStation);
            if(addResult > 0){
                logger.info("新增地铁站数据成功subwayStation:{}",subwayStation);
            }
        }else {
            logger.info("地铁站已经存在subwayStation:{}", JSON.toJSONString(subwayStation));
            addResult = -1;
        }
        return addResult;
    }

    /**
     * 根据id删除地铁站
     * @param stationId
     * @return
     */
    @Override
    public int delSubwayStation(Integer stationId) {
        int delResult = 0;
        try {
            delResult = subwayStationMapper.deleteByPrimaryKey(stationId);
            if(delResult > 0){
                logger.info("删除地铁站数据成功stationId:{}",stationId);

            }
        }catch (Exception e){
            logger.info("删除地铁站数据失败stationId:{}",stationId);
            e.printStackTrace();
        }finally {
            return delResult;
        }
    }

    /**
     * 根据id查找地铁站信息
     * @param stationId
     * @return
     */
    @Override
    public SubwayStation findSubwayStationById(Integer stationId) {
        SubwayStation subwayStation = subwayStationMapper.selectByPrimaryKey(stationId);
        return null;
    }
}
