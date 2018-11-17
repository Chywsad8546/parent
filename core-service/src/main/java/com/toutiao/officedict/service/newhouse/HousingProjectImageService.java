package com.toutiao.officedict.service.newhouse;

import com.toutiao.officedict.dao.entity.officedict.ProjImage;
import com.toutiao.officedict.domain.query.HousingProjectImageQuery;
import java.util.List;

/**
 * 楼盘图片服务
 * @author WuShoulei on 2017/11/28
 */
public interface HousingProjectImageService {

    /**
     * 楼盘户型图列表
     * @param imageQuery
     * @return
     */
    List<ProjImage> listHousingProjectLayoutImages(HousingProjectImageQuery imageQuery);

    /**
     * 除户型图外的图片列表
     * @param imageQuery
     * @return
     */
    List<ProjImage> listHousingProjectOtherImages(HousingProjectImageQuery imageQuery);

    /**
     * 二手房外景图列表
     * @param imageQUery
     * @return
     */
    List<ProjImage> esfPhotosList(HousingProjectImageQuery imageQUery);

    /**
     * 保存除户型图外的其他图片
     * @param image
     * @return
     */
    int saveHousingProjectOtherImage(ProjImage image);

    /**
     * 删除楼盘除户型图外的某张图片
     * @param imageId
     * @return
     */
    int deleteHousingProjectOtherImage(Integer imageId);

    /**
     * 将该图片作为标题图
     * @param imageId
     * @return
     */
    int titleHousingProjectOtherImage(Integer imageId);

    /**
     * 合格或不合格
     * @param projImage
     * @return
     */
    int setQualified(ProjImage projImage);


    /**
     * 取消该标题图
     * @param imageId
     * @return
     */
    int cancelTitleHousingProjectOtherImage(Integer imageId);

}
