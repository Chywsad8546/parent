package com.toutiao.officedict.service.newhouse.impl;

import com.github.pagehelper.PageHelper;
import com.toutiao.officedict.dao.entity.officedict.ProjImage;
import com.toutiao.officedict.dao.entity.officedict.ProjectInfo;
import com.toutiao.officedict.dao.mapper.officedict.ProjImageMapper;
import com.toutiao.officedict.dao.mapper.officedict.ProjectInfoMapper;
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

    @Autowired
    private ProjectInfoMapper infoMapper;

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
        imageMapper.insertSelective(image);

        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setNewcode(image.getNewcode());

        return infoMapper.updateByPrimaryKeySelective(projectInfo);
    }

    @Override
    public int deleteHousingProjectOtherImage(Integer imageId) {

        ProjImage projImage = imageMapper.selectByPrimaryKey(imageId);
        imageMapper.deleteByPrimaryKey(imageId);

        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setNewcode(projImage.getNewcode());

        return infoMapper.updateByPrimaryKeySelective(projectInfo);
    }

    @Override
    public int titleHousingProjectOtherImage(Integer imageId) {
        ProjImage pi = new ProjImage();
        pi.setId(imageId);
        pi.setIsLogPic(1);

        imageMapper.updateByPrimaryKeySelective(pi);

        ProjImage projImage = imageMapper.selectByPrimaryKey(imageId);
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setNewcode(projImage.getNewcode());

        return infoMapper.updateByPrimaryKeySelective(projectInfo);
    }

    @Override
    public int setQualified(ProjImage projImage) {
        imageMapper.updateByPrimaryKeySelective(projImage);

        projImage = imageMapper.selectByPrimaryKey(projImage.getId());
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setNewcode(projImage.getNewcode());

        return infoMapper.updateByPrimaryKeySelective(projectInfo);
    }

    @Override
    public int cancelTitleHousingProjectOtherImage(Integer imageId) {
        ProjImage pi = new ProjImage();
        pi.setId(imageId);
        pi.setIsLogPic(0);
        imageMapper.updateByPrimaryKeySelective(pi);

        ProjImage projImage = imageMapper.selectByPrimaryKey(imageId);
        ProjectInfo projectInfo = new ProjectInfo();
        projectInfo.setNewcode(projImage.getNewcode());

        return infoMapper.updateByPrimaryKeySelective(projectInfo);
    }
}
