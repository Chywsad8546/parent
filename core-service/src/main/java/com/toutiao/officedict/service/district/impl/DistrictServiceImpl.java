package com.toutiao.officedict.service.district.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.District;
import com.toutiao.officedict.dao.entity.officedict.DistrictAreaConfig;
import com.toutiao.officedict.dao.mapper.officedict.DistrictAreaConfigMapper;
import com.toutiao.officedict.dao.mapper.officedict.DistrictMapper;
import com.toutiao.officedict.domain.district.DistrictDomain;
import com.toutiao.officedict.domain.query.DistrictAreaConfigQuery;
import com.toutiao.officedict.domain.query.DistrictQuery;
import com.toutiao.officedict.service.district.DistrictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService{

    private Logger logger = LoggerFactory.getLogger(DistrictServiceImpl.class);

    @Autowired
    private DistrictMapper districtMapper;
    @Autowired
    private DistrictAreaConfigMapper districtAreaConfigMapper;

    /**
     * 获取区县列表
     * @param districtQuery
     * @return
     */
    @Override
    public List<DistrictDomain> getDistrictLists(DistrictQuery districtQuery) {

        PageHelper.startPage(districtQuery.getPageNum(), districtQuery.getPageSize());
        List<DistrictDomain> districtList = new ArrayList<>();
        try {
            districtList = districtMapper.selectDistrictList(districtQuery);
            logger.info("查询区县列表districtList:{}",districtList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return districtList;
    }

    /**
     * 根据区县id删除区县
     * @param districtId
     * @return
     */
    @Override
    public int delDistrict(Integer districtId) {
        int delResult = 0;
        DistrictAreaConfigQuery districtAreaConfigQuery = new DistrictAreaConfigQuery();
        districtAreaConfigQuery.setDistrictId(districtId);
        List<DistrictAreaConfig> list = districtAreaConfigMapper.selectByDistrictId(districtAreaConfigQuery);
        if(list.size() > 0){
            logger.info("该区县已与商圈绑定，不能删除districtId:{}",districtId);
            delResult = -1;
        } else {
            delResult = districtMapper.deleteByPrimaryKey(districtId);
        }
        return delResult;

    }

    /**
     * 添加区县信息
     * @param district
     * @return
     */
    @Override
    public int addDistrict(District district) {
        int addResult = 0;
        District dist = districtMapper.selectByDistrictName(district.getDistrictName());
        if(null == dist){
            addResult = districtMapper.insertSelective(district);
            if(addResult > 0){
                logger.info("插入区县数据成功district:{}", JSON.toJSONString(district));
            }
        } else{
            logger.info("区县已经存在district:{}", JSON.toJSONString(district));
            addResult = -1;
        }
        return addResult;

    }

    /**
     * 更新区县信息
     * @param district
     * @return
     */
    @Override
    public int updateDistrict(District district) {
        int updateResult = 0;
        try{
            updateResult = districtMapper.updateByPrimaryKeySelective(district);
            if (updateResult > 0){
                logger.info("更新区县数据成功district:{}",district);
            }
        }catch (Exception e){
            logger.info("更新区县数据失败district:{}",district);
            e.printStackTrace();
        }finally {

            return updateResult;
        }
    }

    /**
     * 根据id查询区县信息
     * @param districtId
     * @return
     */
    @Override
    public District findDistrictById(Integer districtId) {

        District district = districtMapper.selectByPrimaryKey(districtId);

        return district;
    }
}
