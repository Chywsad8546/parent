package com.toutiao.officedict.service.global.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.ProjPrice;
import com.toutiao.officedict.dao.mapper.officedict.ProjPriceMapper;
import com.toutiao.officedict.domain.query.ProjectPriceQuery;
import com.toutiao.officedict.service.global.ProjectPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WuShoulei on 2017/11/24
 */
@Service
public class ProjectPriceServiceImpl implements ProjectPriceService {

    @Autowired
    private ProjPriceMapper priceMapper;

    @Override
    public List<ProjPrice> listProjectPrice(ProjectPriceQuery priceQuery) {

        PageHelper.startPage(priceQuery.getPageNum(), priceQuery.getPageSize());
        return priceMapper.listProjectPrice(priceQuery);
    }

    @Override
    public int saveProjectPrice(ProjPrice price) {
        return priceMapper.insertSelective(price);
    }

    @Override
    public ProjPrice getProjectPrice(Integer priceId) {
        return priceMapper.selectByPrimaryKey(priceId);
    }

    @Override
    public int updateProjectPrice(ProjPrice price) {
        return priceMapper.updateByPrimaryKeySelective(price);
    }

    @Override
    public int deleteProjectPrice(Integer priceId) {
        return priceMapper.deleteByPrimaryKey(priceId);
    }
}
