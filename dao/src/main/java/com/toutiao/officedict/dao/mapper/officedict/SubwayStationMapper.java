package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.SubwayStation;
import com.toutiao.officedict.domain.query.SubwayQuery;

import java.util.List;

public interface SubwayStationMapper extends BaseDao {
    int deleteByPrimaryKey(Integer stationId);

    int insert(SubwayStation record);

    int insertSelective(SubwayStation record);

    SubwayStation selectByPrimaryKey(Integer stationId);

    int updateByPrimaryKeySelective(SubwayStation record);

    int updateByPrimaryKey(SubwayStation record);

    /**
     * 根据地铁线删除地铁站
     * @param lineId
     * @return
     */
    int deleteBylineId(Integer lineId);

    /**
     * 查询line id查询地铁站
     * @param subwayQuery
     * @return
     */
    List<SubwayStation> selectSubwayStationList(SubwayQuery subwayQuery);

    /**
     * 根据名称查询
     * @param stationName
     * @return
     */
    SubwayStation selectBySubwayStationName(String stationName);
}