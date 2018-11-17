package com.toutiao.officedict.apiimpl.controller.global;

import com.qiniu.storage.model.DefaultPutRet;
import com.toutiao.officedict.api.chance.request.ProjectImageRequest;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.apiimpl.controller.BaseController;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.common.util.UploadUtil;
import com.toutiao.officedict.dao.entity.officedict.ProjImage;
import com.toutiao.officedict.domain.query.HousingProjectImageQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectImageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 楼盘图片服务
 * @author WuShoulei on 2017/11/28
 */
@RequestMapping("/pic")
@RestController
public class HousingProjectImageRestful extends BaseController {

    @Autowired
    private HousingProjectImageService imageService;

    /**
     * 二手房外景图列表
     * @param imageQuery
     * @return
     */
    @RequestMapping(value = "/esfPhotos")
    @ResponseBody
    public NashResult esfPhotosList(HousingProjectImageQuery imageQuery) {
        SerializableData serializableData = User.getCurrent().getSerializableData();
        imageQuery.setCityId(serializableData.getCityId());
        List<ProjImage> otherImageList = imageService.esfPhotosList(imageQuery);

        return NashResult.build(otherImageList);
    }

    /**
     * 楼盘除户型图外的其他图片列表
     * @param imageQuery
     * @return
     */
    @RequestMapping(value = "/listHousingProjectOtherImages")
    @ResponseBody
    public NashResult listHousingProjectOtherImages(@Validated(Third.class) HousingProjectImageQuery imageQuery) {

        List<ProjImage> otherImageList = imageService.listHousingProjectOtherImages(imageQuery);

        return NashResult.build(otherImageList);
    }

    /**
     * 保存楼盘图片
     * @param imageRequest
     * @return
     */
    @RequestMapping(value = "/saveHousingProjectOtherImage", method = RequestMethod.POST)
    @ResponseBody
    public NashResult saveHousingProjectOtherImage(@Validated(Second.class) ProjectImageRequest imageRequest,
                                                   @ModelAttribute("user") User user) {
        imageRequest.setCreatorId(user.getUserId());

        return  uploadProjectImage(imageRequest);
    }

    /**
     * 删除楼盘除户型图外的某张图片
     * @param imageId
     * @return
     */
    @RequestMapping(value = "/deleteHousingProjectOtherImage")
    @ResponseBody
    public NashResult deleteHousingProjectOtherImage(@RequestParam(value = "id") Integer imageId) {

        return NashResult.build(imageService.deleteHousingProjectOtherImage(imageId));
    }
    /**
     * 将该图片作为标题图
     * @param imageId
     * @return
     */
    @RequestMapping(value = "/titleHousingProjectOtherImage")
    @ResponseBody
    public NashResult titleHousingProjectOtherImage(@RequestParam(value = "id") Integer imageId) {

        return NashResult.build(imageService.titleHousingProjectOtherImage(imageId));
    }

    /**
     * 合格或不合格
     * @param projImage
     * @return
     */
    @RequestMapping(value = "/setQualified")
    @ResponseBody
    public NashResult setQualified(ProjImage projImage) {

        return NashResult.build(imageService.setQualified(projImage));
    }
    /**
     * 取消标题图
     * @param imageId
     * @return
     */
    @RequestMapping(value = "/cancelTitleHousingProjectOtherImage")
    @ResponseBody
    public NashResult cancelTitleHousingProjectOtherImage(@RequestParam(value = "id") Integer imageId) {

        return NashResult.build(imageService.cancelTitleHousingProjectOtherImage(imageId));
    }

    //上传图片
    private NashResult uploadProjectImage(ProjectImageRequest imageRequest) {

        MultipartFile tempFile = imageRequest.getFile();

        NashResult tempResult = UploadUtil.uploadImage(tempFile);

        if ("success".equals(tempResult.getCode())){
            DefaultPutRet putRet = (DefaultPutRet) tempResult.getData();
            if (putRet != null) {
                imageRequest.setImgPath(putRet.key);
            }
        }

        ProjImage image = new ProjImage();
        BeanUtils.copyProperties(imageRequest, image);
        image.setImgSize((int)tempFile.getSize()/1024);//图片大小，单位KB
        image.setImgResolution(UploadUtil.getImageResolution(tempFile));

        return NashResult.build(imageService.saveHousingProjectOtherImage(image));
    }
}