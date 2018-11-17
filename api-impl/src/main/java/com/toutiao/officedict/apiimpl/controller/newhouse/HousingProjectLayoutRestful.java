package com.toutiao.officedict.apiimpl.controller.newhouse;

import com.qiniu.storage.model.DefaultPutRet;
import com.toutiao.officedict.api.chance.request.LayoutImageRequest;
import com.toutiao.officedict.api.chance.request.newhouse.ProjectLayoutRequest;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.apiimpl.controller.BaseController;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.assertUtils.Third;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.common.util.UploadUtil;
import com.toutiao.officedict.dao.entity.officedict.ProjectLayout;
import com.toutiao.officedict.dao.entity.officedict.ProjectLayoutImage;
import com.toutiao.officedict.domain.query.ProjectLayoutQuery;
import com.toutiao.officedict.service.newhouse.HousingProjectLayoutService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 楼盘户型管理
 * @author WuShoulei on 2017/12/2
 */
@RestController
@RequestMapping("/newHouse")
public class HousingProjectLayoutRestful extends BaseController {

    @Autowired
    private HousingProjectLayoutService layoutService;

    /**
     * 楼盘户型列表-新房
     * @param layoutQuery
     * @return
     */
    @RequestMapping(value = "/listHousingProjectLayout")
    @ResponseBody
    public NashResult listHousingProjectLayout(@Validated ProjectLayoutQuery layoutQuery) {

        List<ProjectLayout> layoutList = layoutService.listHousingProjectLayout(layoutQuery);

        return NashResult.build(layoutList);
    }

    /**
     * 新增楼盘户型-新房
     * @param layoutRequest
     * @return
     */
    @RequestMapping(value = "/saveHousingProjectLayout", method = RequestMethod.POST)
    @ResponseBody
    public NashResult saveHousingProjectLayout(@Validated(Second.class) ProjectLayoutRequest layoutRequest,
                                               @ModelAttribute("user") User user) {

        layoutRequest.setCreatorId(user.getUserId());

        ProjectLayout layout = new ProjectLayout();
        BeanUtils.copyProperties(layoutRequest, layout);

        return NashResult.build(layoutService.saveHousingProjectLayout(layout));
    }

    /**
     * 楼盘户型更新-新房
     * @param layoutRequest
     * @return
     */
    @RequestMapping(value = "/updateHousingProjectLayout", method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateHousingProjectLayout(@Validated(First.class) ProjectLayoutRequest layoutRequest,
                                                 @ModelAttribute("user") User user) {

        layoutRequest.setUpdaterId(user.getUserId());

        ProjectLayout layout = new ProjectLayout();
        BeanUtils.copyProperties(layoutRequest, layout);


        return NashResult.build(layoutService.updateHousingProjectLayout(layout));
    }

    /**
     * 楼盘户型查询-新房
     * @param layoutId
     * @return
     */
    @RequestMapping(value = "/getHousingProjectLayout")
    @ResponseBody
    public NashResult getHousingProjectLayout(@RequestParam(value = "id") Integer layoutId) {

        return NashResult.build(layoutService.getHousingProjectLayout(layoutId));
    }

    /**
     * 楼盘户型删除-新房
     * @param layoutRequest
     * @return
     */
    @RequestMapping(value = "/deleteHousingProjectLayout", method = RequestMethod.POST)
    @ResponseBody
    public NashResult deleteHousingProjectLayout(@Validated(Third.class) ProjectLayoutRequest layoutRequest,
                                                 @ModelAttribute("user") User user) {

        layoutRequest.setUpdaterId(user.getUserId());

        ProjectLayout layout = new ProjectLayout();
        BeanUtils.copyProperties(layoutRequest, layout);

        return NashResult.build(layoutService.deleteHousingProjectLayout(layout));
    }

    /**
     * 户型-户型图列表
     * @param layoutId
     * @return
     */
    @RequestMapping(value = "/listLayoutImages")
    @ResponseBody
    public NashResult listHousingProjectLayoutImages(@RequestParam("id") Integer layoutId) {

        List<ProjectLayoutImage> layoutImageList = layoutService.listLayoutImages(layoutId);

        return NashResult.build(layoutImageList);
    }

    /**
     * 保存楼盘户型图
     * @param layoutImageRequest
     * @return
     */
    @RequestMapping(value = "/saveLayoutImage", method = RequestMethod.POST)
    @ResponseBody
    public NashResult saveHousingProjectLayoutImage(@Validated LayoutImageRequest layoutImageRequest,
                                                    @ModelAttribute("user") User user) {

        layoutImageRequest.setCreatorId(user.getUserId());

        return uploadProjectImage(layoutImageRequest);
    }

    /**
     * 删除一张楼盘户型图
     * @param imageId
     * @return
     */
    @RequestMapping(value = "/deleteLayoutImage")
    @ResponseBody
    public NashResult deleteHousingProjectLayoutImage(@RequestParam(value = "id") Integer imageId) {

        return NashResult.build(layoutService.deleteLayoutImage(imageId));
    }

    //上传图片
    private NashResult uploadProjectImage(LayoutImageRequest imageRequest) {

        MultipartFile tempFile = imageRequest.getFile();

        NashResult tempResult = UploadUtil.uploadImage(tempFile);

        if ("success".equals(tempResult.getCode())){
            DefaultPutRet putRet = (DefaultPutRet) tempResult.getData();
            if (putRet != null) {
                imageRequest.setImgPath(putRet.key);
            }
        }

        ProjectLayoutImage layoutImage = new ProjectLayoutImage();
        BeanUtils.copyProperties(imageRequest, layoutImage);
        //图片大小，单位KB
        layoutImage.setImgSize((int)tempFile.getSize()/1024);
        layoutImage.setImgResolution(UploadUtil.getImageResolution(tempFile));

        return NashResult.build(layoutService.saveLayoutImage(layoutImage));
    }
}
