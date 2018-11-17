package com.toutiao.officedict.service.newhouse.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.ProjImage;
import com.toutiao.officedict.dao.mapper.officedict.ProjImageMapper;
import com.toutiao.officedict.domain.query.HousingProjectImageQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WuShoulei on 2017/11/28
 */
@Service
public class HousingProjectImageServiceImpl implements HousingProjectImageService {


    @Autowired
    private ProjImageMapper imageMapper;

    @Override
    public List<ProjImage> listHousingProjectLayoutImages(HousingProjectImageQuery imageQuery) {

        PageHelper.startPage(imageQuery.getPageNum(), imageQuery.getPageSize());
        return imageMapper.listHousingProjectLayoutImages(imageQuery);
    }

    @Override
    public List<ProjImage> listHousingProjectOtherImages(HousingProjectImageQuery imageQuery) {
        //PageHelper.startPage(imageQuery.getPageNum(), imageQuery.getPageSize());
        if(imageQuery.getImgType().equals(0)){
            List<Integer> array = new ArrayList<>();
            array.add(1);array.add(2);array.add(4);
            imageQuery.setImgTypeArray(array);

            List<ProjImage> projImages = imageMapper.listTitleHousingProjectOtherImages(imageQuery);
            //判断集合当中是否有标题图
            boolean flag = false;
            for (ProjImage pi : projImages) {
                if(pi.getIsLogPic()==1){
                    flag=true;
                    break;
                }
            }
            if (flag){
                for (ProjImage image : projImages) {
                    image.setRemark(1);
                }
            }
            return projImages;
        }


        return imageMapper.listHousingProjectOtherImages(imageQuery);
    }

    @Override
    public List<ProjImage> esfPhotosList(HousingProjectImageQuery imageQuery) {
        if(imageQuery.getImgType().equals(0)){
            List<Integer> array = new ArrayList<>();
            array.add(1);array.add(2);array.add(4);
            imageQuery.setImgTypeArray(array);

            List<ProjImage> projImages = imageMapper.listTitleHousingProjectOtherImages(imageQuery);
            //判断集合当中是否有标题图
            boolean flag = false;
            for (ProjImage pi : projImages) {
                if(pi.getIsLogPic()==1){
                    flag=true;
                    break;
                }
            }
            if (flag){
                for (ProjImage image : projImages) {
                    image.setRemark(1);
                }
            }
            return projImages;
        }

        PageHelper.startPage(imageQuery.getPageNum(), imageQuery.getPageSize());
        return imageMapper.esfPhotosList(imageQuery);
    }

    @Override
    public int saveHousingProjectOtherImage(ProjImage image) {
        return imageMapper.insertSelective(image);
    }

    @Override
    public int deleteHousingProjectOtherImage(Integer imageId) {
        return imageMapper.deleteByPrimaryKey(imageId);
    }

    @Override
    public int titleHousingProjectOtherImage(Integer imageId) {
        ProjImage pi = new ProjImage();
        pi.setId(imageId);
        pi.setIsLogPic(1);
        return imageMapper.updateByPrimaryKeySelective(pi);
    }

    @Override
    public int setQualified(ProjImage projImage) {
        return imageMapper.updateByPrimaryKeySelective(projImage);
    }

    @Override
    public int cancelTitleHousingProjectOtherImage(Integer imageId) {
        ProjImage pi = new ProjImage();
        pi.setId(imageId);
        pi.setIsLogPic(0);
        return imageMapper.updateByPrimaryKeySelective(pi);
    }
}
