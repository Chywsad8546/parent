package com.toutiao.officedict.service.subway.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.dao.entity.officedict.SubwayLine;
import com.toutiao.officedict.dao.mapper.officedict.SubwayLineMapper;
import com.toutiao.officedict.dao.mapper.officedict.SubwayStationMapper;
import com.toutiao.officedict.domain.query.SubwayQuery;
import com.toutiao.officedict.service.subway.SubwayLineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubwayLineServiceImpl implements SubwayLineService {

    private Logger logger = LoggerFactory.getLogger(SubwayLineServiceImpl.class);

    @Autowired
    private SubwayLineMapper subwayLineMapper;
    @Autowired
    private SubwayStationMapper subwayStationMapper;

    /**
     * 查询地铁列表
     * @param subwayQuery
     * @return
     */
    @Override
    public List<SubwayLine> getSubwayLineList(SubwayQuery subwayQuery) {
        PageHelper.startPage(subwayQuery.getPageNum(), subwayQuery.getPageSize());
        List<SubwayLine> subwayLineList = new ArrayList<>();
        try {
            subwayLineList = subwayLineMapper.selectSubwayList(subwayQuery);
            logger.info("查询地铁线列表subwayLineList:{}",subwayLineList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return subwayLineList;
    }

    /**
     * 根据line id删除line
     * @param lineId
     * @return
     */
    @Override
    public int delSubway(Integer lineId) {
        int delResult = 0;
        try {
            delResult = subwayLineMapper.deleteByPrimaryKey(lineId);
            if(delResult > 0){
                logger.info("删除地铁线数据成功lineId:{}",lineId);
                int result = subwayStationMapper.deleteBylineId(lineId);
                if (result > 0){
                    logger.info("删除地铁站数据成功lineId:{}",lineId);
                }else {
                    logger.info("删除地铁站数据失败lineId:{}",lineId);
                    return delResult;
                }
            }else{
                logger.info("删除地铁线数据失败lineId:{}",lineId);
                return delResult;
            }
        }catch (Exception e){
            logger.info("删除地铁线数据失败lineId:{}",lineId);
            e.printStackTrace();
        }finally {
            return delResult;
        }
    }

    @Override
    public int addSubway(SubwayLine subwayLine) {
        int addResult = 0;
        SubwayLine sL = subwayLineMapper.selectBySubwayName(subwayLine.getSubwayName());
        if(null == sL){
            addResult = subwayLineMapper.insertSelective(subwayLine);
            if(addResult > 0){
                logger.info("新增地铁线数据成功subwayLine:{}",subwayLine);
            }
        }else{
            logger.info("地铁线已经存在subwayLine:{}", JSON.toJSONString(subwayLine));
            addResult = -1;
        }

        return addResult;
    }

    @Override
    public int updateSubwayLine(SubwayLine subwayLine) {
        int updateResult = 0;
        try{
            updateResult = subwayLineMapper.updateByPrimaryKeySelective(subwayLine);
            if (updateResult > 0){
                logger.info("更新地铁线数据成功subwayLine:{}",subwayLine);
            }
        }catch (Exception e){
            logger.info("更新地铁线数据失败subwayLine:{}",subwayLine);
            e.printStackTrace();
        }finally {

            return updateResult;
        }


    }

    @Override
    public SubwayLine findSubwayLineById(Integer lineId) {
        SubwayLine subwayLine = subwayLineMapper.selectByPrimaryKey(lineId);
        return subwayLine;
    }
}
