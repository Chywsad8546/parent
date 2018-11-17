package com.toutiao.officedict.service.mapping.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.mapper.mapping.HouseCommunityNotMatchMapper;
import com.toutiao.officedict.domain.mapping.NotMatchModel;
import com.toutiao.officedict.domain.query.mapping.NotMatchQuery;
import com.toutiao.officedict.service.mapping.NoMatchMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WuShoulei on 2018/7/16
 */
@Service
public class NoMatchMappingServiceImpl implements NoMatchMappingService {

    @Autowired
    private HouseCommunityNotMatchMapper notMatchMapper;

    /**
     * 楼盘未匹配列表
     * @param notMatchQuery
     * @return
     */
    @Override
    public List<NotMatchModel> listNotMatchMappings(NotMatchQuery notMatchQuery) {

        PageHelper.startPage(notMatchQuery.getPageNum(), notMatchQuery.getPageSize());
        List<NotMatchModel> notMatchList = notMatchMapper.listNotMatch(notMatchQuery);
        return notMatchList;
    }

    @Override
    public NotMatchModel selectNotMatch(Integer id) {

        NotMatchModel notMatchList = notMatchMapper.selectNotMatch(id);
        return notMatchList;
    }

    @Override
    public Integer updateStutas(NotMatchQuery notMatchQuery) {
        return  notMatchMapper.updateByPrimaryKeySelective(notMatchQuery);
    }

    @Override
    public Integer deleteRela(Integer id) {
        return notMatchMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<String> getCompanys() {
        return notMatchMapper.getCompanys();
    }

}
