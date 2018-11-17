package com.toutiao.officedict.apiimpl.controller.global;

import com.toutiao.officedict.api.chance.request.ProjPriceRequest;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.apiimpl.controller.BaseController;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.ProjPrice;
import com.toutiao.officedict.domain.query.ProjectPriceQuery;
import com.toutiao.officedict.service.global.ProjectPriceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 楼盘价格管理
 * @author WuShoulei on 2017/11/24
 */
@RestController
@RequestMapping("/price")
public class ProjectPriceRestful extends BaseController {

    @Autowired
    private ProjectPriceService priceService;

    /**
     * 楼盘历史价格列表
     * @return
     */
    @RequestMapping(value = "/listProjectPrice")
    @ResponseBody
    public NashResult listProjectPrice(@Validated ProjectPriceQuery priceQuery) {

        List<ProjPrice> priceList = priceService.listProjectPrice(priceQuery);

        return NashResult.build(priceList);
    }

    /**
     * 添加价格信息
     * @param priceRequest
     * @return
     */
    @RequestMapping(value = "/saveProjectPrice", method = RequestMethod.POST)
    @ResponseBody
    public NashResult addProjectPrice(@Validated(Second.class) ProjPriceRequest priceRequest,
                                      @ModelAttribute("user") User user) {

        priceRequest.setCreatorId(user.getUserId());

        ProjPrice price = new ProjPrice();
        BeanUtils.copyProperties(priceRequest, price);

        return NashResult.build(priceService.saveProjectPrice(price));
    }

    /**
     * 查询某个价格信息
     * @param priceId
     * @return
     */
    @RequestMapping(value = "/getProjectPrice")
    @ResponseBody
    public NashResult getProjectPrice(@RequestParam(value = "id") Integer priceId) {
        ProjPrice price = priceService.getProjectPrice(priceId);

        return NashResult.build(price);
    }

    /**
     * 更新价格信息
     * @param priceRequest
     * @return
     */
    @RequestMapping(value = "/updateProjectPrice", method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateNewHouseHousing(@Validated(First.class) ProjPriceRequest priceRequest,
                                            @ModelAttribute("user") User user) {

        priceRequest.setUpdaterId(user.getUserId());

        ProjPrice price = new ProjPrice();
        BeanUtils.copyProperties(priceRequest, price);

        return NashResult.build(priceService.updateProjectPrice(price));
    }

    /**
     * 删除楼盘价格信息
     * @param priceId
     * @return
     */
    @RequestMapping(value = "/deleteProjectPrice")
    @ResponseBody
    public NashResult deleteProjectPrice(@RequestParam("id") Integer priceId) {

        return NashResult.build(priceService.deleteProjectPrice(priceId));
    }
}
