package com.toutiao.officedict.service.relation.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.ProjectInfo;
import com.toutiao.officedict.dao.entity.officedict.RelationOfMaiTian;
import com.toutiao.officedict.dao.mapper.officedict.ProjectInfoMapper;
import com.toutiao.officedict.dao.mapper.officedict.RelationOfMaiTianMapper;
import com.toutiao.officedict.domain.query.HousingProjectQuery;
import com.toutiao.officedict.domain.query.RelationOfMaiTianQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectService;
import com.toutiao.officedict.service.relation.RelationOfMaiTianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 18710 on 2018/4/11.
 */
@Service
@Transactional
public class RelationOfMaiTianServiceImpl implements RelationOfMaiTianService{

    private Logger logger = LoggerFactory.getLogger(RelationOfMaiTianServiceImpl.class);

    @Autowired
    private RelationOfMaiTianMapper relationOfMaiTianMapper;

    @Autowired
    private HousingProjectService projectService;

    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    @Override
    public List<RelationOfMaiTian> selectRelationOfMaiTian(RelationOfMaiTianQuery relationOfMaiTianQuery) {
        PageHelper.startPage(relationOfMaiTianQuery.getPageNum(), relationOfMaiTianQuery.getPageSize());
        List<RelationOfMaiTian> relationOfMaiTians = relationOfMaiTianMapper.selectRelationOfMaiTian(relationOfMaiTianQuery);
        return relationOfMaiTians;
    }

    @Override
    public RelationOfMaiTian selectOneRelationOfMaiTian(Integer id) {
        return relationOfMaiTianMapper.selectOne(id);
    }

    @Override
    public Integer updateById(RelationOfMaiTianQuery relationOfMaiTianQuery) {

        RelationOfMaiTian relationOfMaiTian = relationOfMaiTianMapper.selectOne(relationOfMaiTianQuery.getId());
        ProjectInfo projectInfo = projectInfoMapper.selectProjectByName(relationOfMaiTianQuery.getProjname());
        relationOfMaiTianQuery.setNewcode(projectInfo.getNewcode());
        projectService.updateProjectNickName(relationOfMaiTian.getNewcode(),
                relationOfMaiTian.getCommunityName(), relationOfMaiTianQuery.getCommunityName());
        return relationOfMaiTianMapper.updateById(relationOfMaiTianQuery);
    }

    /**
     * 保存麦田-头条房产楼盘对应关系
     * @param relationOfMaiTianQuery
     * @return
     */
    @Override
    public Integer saveMaitianMapping(RelationOfMaiTianQuery relationOfMaiTianQuery) {

        ProjectInfo projectInfo = projectInfoMapper.selectProjectByName(relationOfMaiTianQuery.getProjname());

        if (null != projectInfo) {
            relationOfMaiTianQuery.setNewcode(projectInfo.getNewcode());
            Integer seCount = relationOfMaiTianMapper.selectCount(relationOfMaiTianQuery);
            if (seCount==null || seCount ==0){
                projectService.updateProjectNickName(projectInfo.getNewcode(),
                        "", relationOfMaiTianQuery.getCommunityName());
                return relationOfMaiTianMapper.insert(relationOfMaiTianQuery);
            }else {
                return 3;
            }
        }

        return 0;
    }

    @Override
    public Integer selectByTouName(String projname) {
        return relationOfMaiTianMapper.selectByTouName(projname);
    }

    @Override
    public Integer deleteMaiTian(Integer id) {
        return relationOfMaiTianMapper.deleteById(id);
    }
}

