package com.toutiao.officedict.service.subway;

import com.toutiao.officedict.dao.entity.officedict.SubwayLine;
import com.toutiao.officedict.dao.entity.officedict.SubwayStation;
import com.toutiao.officedict.domain.query.SubwayQuery;

import java.util.List;

public interface SubwayStationService {


    /**
     * 查询line id查询地铁站
     * @param subwayQuery
     * @return
     */
    List<SubwayStation> getSubwayStationList(SubwayQuery subwayQuery);

    /**
     * 修改地铁站
     * @param subwayStation
     * @return
     */
    int updateSubwayStation(SubwayStation subwayStation);

    /**
     * 新增地铁站
     * @param subwayStation
     * @return
     */
    int addSubwayStation(SubwayStation subwayStation);

    /**
     * 删除地铁站
     * @param stationId
     * @return
     */
    int delSubwayStation(Integer stationId);

    /**
     * 根据id查找地铁站信息
     * @param stationId
     * @return
     */
    SubwayStation findSubwayStationById(Integer stationId);
}
