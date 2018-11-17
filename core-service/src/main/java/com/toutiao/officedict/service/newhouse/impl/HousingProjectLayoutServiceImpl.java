package com.toutiao.officedict.service.newhouse.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.ProjectLayout;
import com.toutiao.officedict.dao.entity.officedict.ProjectLayoutImage;
import com.toutiao.officedict.dao.mapper.officedict.ProjectLayoutImageMapper;
import com.toutiao.officedict.dao.mapper.officedict.ProjectLayoutMapper;
import com.toutiao.officedict.domain.query.ProjectLayoutQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectLayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WuShoulei on 2017/12/22
 */
@Service
@Transactional
public class HousingProjectLayoutServiceImpl implements HousingProjectLayoutService {

    @Autowired
    private ProjectLayoutMapper layoutMapper;

    @Autowired
    private ProjectLayoutImageMapper layoutImageMapper;

    @Override
    public int saveHousingProjectLayout(ProjectLayout layout) {
        return layoutMapper.insertSelective(layout);
    }

    @Override
    public List<ProjectLayout> listHousingProjectLayout(ProjectLayoutQuery layoutQuery) {

        PageHelper.startPage(layoutQuery.getPageNum(), layoutQuery.getPageSize());
        return layoutMapper.listHousingProjectLayout(layoutQuery);
    }

    @Override
    public int updateHousingProjectLayout(ProjectLayout layout) {
        return layoutMapper.updateByPrimaryKeySelective(layout);
    }

    @Override
    public ProjectLayout getHousingProjectLayout(Integer layoutId) {
        return layoutMapper.selectByPrimaryKey(layoutId);
    }

    @Override
    public int deleteHousingProjectLayout(ProjectLayout layout) {
        return layoutMapper.updateLayoutDeleteStatus(layout);
    }

    /**
     * 户型图列表
     * @param layoutId
     * @return
     */
    @Override
    public List<ProjectLayoutImage> listLayoutImages(Integer layoutId) {
        return layoutImageMapper.listLayoutImages(layoutId);
    }

    /**
     * 删除户型图
     * @param imageId
     * @return
     */
    @Override
    public int deleteLayoutImage(Integer imageId) {
        int result = layoutImageMapper.deleteByPrimaryKey(imageId);

        ProjectLayoutImage layoutImage = layoutImageMapper.selectByPrimaryKey(imageId);

        //处理完图片之后，更新户型信息，以便信息同步到前台
        if (null != layoutImage) {
            ProjectLayout projectLayout = new ProjectLayout();
            projectLayout.setLayoutId(layoutImage.getLayoutId());
            layoutMapper.updateByPrimaryKeySelective(projectLayout);
        }

        return result;
    }

    /**
     * 保存户型图
     * @param layoutImage
     * @return
     */
    @Override
    public int saveLayoutImage(ProjectLayoutImage layoutImage) {
        int result = layoutImageMapper.insertSelective(layoutImage);

        //处理完图片之后，更新户型信息，以便信息同步到前台
        if (null != layoutImage.getLayoutId()){
            ProjectLayout projectLayout = new ProjectLayout();
            projectLayout.setLayoutId(layoutImage.getLayoutId());
            layoutMapper.updateByPrimaryKeySelective(projectLayout);
        }
        return result;
    }
}
