package com.toutiao.officedict.service.newhouse;

import com.toutiao.officedict.dao.entity.officedict.ProjectLayout;
import com.toutiao.officedict.dao.entity.officedict.ProjectLayoutImage;
import com.toutiao.officedict.domain.query.HousingProjectImageQuery;
import com.toutiao.officedict.domain.query.ProjectLayoutQuery;

import java.util.List;

/**
 * 楼盘户型服务
 * @author WuShoulei on 2017/12/22
 */
public interface HousingProjectLayoutService {

    /**
     * 新增楼盘户型-新房
     * @param layout
     * @return
     */
    int saveHousingProjectLayout(ProjectLayout layout);

    /**
     * 楼盘户型列表-新房
     * @param layoutQuery
     * @return
     */
    List<ProjectLayout> listHousingProjectLayout(ProjectLayoutQuery layoutQuery);

    /**
     * 楼盘户型更新-新房
     * @param layout
     * @return
     */
    int updateHousingProjectLayout(ProjectLayout layout);

    /**
     * 楼盘户型查询-新房
     * @param layoutId
     * @return
     */
    ProjectLayout getHousingProjectLayout(Integer layoutId);

    /**
     * 楼盘户型删除-新房
     * @param layout
     * @return
     */
    int deleteHousingProjectLayout(ProjectLayout layout);

    /**
     * 户型图列表
     * @param layoutId
     * @return
     */
    List<ProjectLayoutImage> listLayoutImages(Integer layoutId);

    /**
     * 删除户型图
     * @param imageId
     * @return
     */
    int deleteLayoutImage(Integer imageId);

    /**
     * 保存户型图
     * @param layoutImage
     * @return
     */
    int saveLayoutImage(ProjectLayoutImage layoutImage);
}
