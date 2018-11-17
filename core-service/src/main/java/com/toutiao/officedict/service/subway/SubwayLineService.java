package com.toutiao.officedict.service.subway;

import com.toutiao.officedict.dao.entity.officedict.SubwayLine;
import com.toutiao.officedict.domain.query.SubwayQuery;

import java.util.List;

public interface SubwayLineService {

    /**
     * 查询地铁列表
     * @param subwayQuery
     * @return
     */
    List<SubwayLine> getSubwayLineList(SubwayQuery subwayQuery);

    /**
     * 根据id删除地铁
     * @param lineId
     * @return
     */
    int delSubway(Integer lineId);

    /**
     * 添加地铁线
     * @param subwayLine
     * @return
     */
    int addSubway(SubwayLine subwayLine);

    /**
     * 更新地铁线
     * @param subwayLine
     * @return
     */
    int updateSubwayLine(SubwayLine subwayLine);

    /**
     * 根据地铁线id查询详情
     * @param lineId
     * @return
     */
    SubwayLine findSubwayLineById(Integer lineId);


}
