package com.toutiao.officedict.dao.mapper.officedict;

import com.toutiao.officedict.dao.BaseDao;
import com.toutiao.officedict.dao.entity.officedict.ProjImage;
import com.toutiao.officedict.domain.query.HousingProjectImageQuery;

import java.util.List;

public interface ProjImageMapper extends BaseDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ProjImage record);

    int insertSelective(ProjImage record);

    ProjImage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProjImage record);

    int updateByPrimaryKey(ProjImage record);

    /**
     * 批量保存图片
     * @param projImageList
     * @return
     */
    int saveProjImages(List<ProjImage> projImageList);

    /**
     * 查询除户型图外的图片列表
     * @param imageQuery
     * @return
     */
    List<ProjImage> listHousingProjectOtherImages(HousingProjectImageQuery imageQuery);

    /**
     * 二手房外景图列表
     * @param imageQuery
     * @return
     */
    List<ProjImage> esfPhotosList(HousingProjectImageQuery imageQuery);


    /**
     * 获取户型图列表
     * @param imageQuery
     * @return
     */
    List<ProjImage> listHousingProjectLayoutImages(HousingProjectImageQuery imageQuery);

    /**
     * 获取可作为标题图的所有图片
     * @param imageQuery
     * @return
     */
    List<ProjImage>  listTitleHousingProjectOtherImages(HousingProjectImageQuery imageQuery);
}