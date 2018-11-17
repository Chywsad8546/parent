package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.dao.entity.officedict.SubwayLine;
import com.toutiao.officedict.domain.query.AreaQuery;
import com.toutiao.officedict.domain.query.SubwayQuery;

import java.util.List;

public interface SubwayLineMapper extends BaseDao {
    int deleteByPrimaryKey(Integer lineId);

    int insert(SubwayLine record);

    int insertSelective(SubwayLine record);

    SubwayLine selectByPrimaryKey(Integer lineId);

    int updateByPrimaryKeySelective(SubwayLine record);

    int updateByPrimaryKey(SubwayLine record);

    /**
     * 地铁线列表
     */
    List<SubwayLine> selectSubwayList(SubwayQuery subwayQuery);

    /**
     * 根据名称查询
     * @param subwayName
     * @return
     */
    SubwayLine selectBySubwayName(String subwayName);
}