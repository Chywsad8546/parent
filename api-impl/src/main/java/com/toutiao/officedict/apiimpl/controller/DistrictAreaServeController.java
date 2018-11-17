package com.toutiao.officedict.apiimpl.controller;


import com.toutiao.officedict.api.chance.request.BindingDistrictAreaRequest;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.Area;
import com.toutiao.officedict.dao.entity.officedict.District;
import com.toutiao.officedict.dao.entity.officedict.DistrictAreaConfig;
import com.toutiao.officedict.domain.districtArea.AreaWithDistrictID;
import com.toutiao.officedict.domain.districtArea.DistrictAreaDomain;
import com.toutiao.officedict.domain.query.DistrictAreaConfigQuery;
import com.toutiao.officedict.service.districtAreaConfig.DistrictAreaConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value= "/districtarea")
public class DistrictAreaServeController {

    private Logger logger = LoggerFactory.getLogger(DistrictAreaServeController.class);

    @Autowired
    private DistrictAreaConfigService districtAreaConfigService;

    /**
     * 区域商圈绑定页面
     * @param districtAreaConfigQuery
     * @return
     */
    @RequestMapping("/config-list")
    @ResponseBody
    public NashResult getCityList(DistrictAreaConfigQuery districtAreaConfigQuery){
        List<DistrictAreaDomain> areaList = districtAreaConfigService.findDistrictAreaConfig(districtAreaConfigQuery);
        return NashResult.build(areaList);

    }

    /**
     * 查询商圈对应区域id
     * @param districtAreaConfigQuery
     * @return
     */
    @RequestMapping("/getDistrictId")
    @ResponseBody
    public NashResult getDistrictId(DistrictAreaConfigQuery districtAreaConfigQuery){
        List<DistrictAreaConfig> areaList = districtAreaConfigService.getDistrictId(districtAreaConfigQuery);
        return NashResult.build(areaList);

    }

    /**
     * 绑定区县商圈
     * @param bindingDistrictAreaRequest
     * @return
     */
    @RequestMapping("/binding")
    @ResponseBody
    public NashResult binding(BindingDistrictAreaRequest bindingDistrictAreaRequest){
        logger.info("绑定区县商圈关系入参bindingDistrictAreaRequest:{}",bindingDistrictAreaRequest);
        DistrictAreaConfig districtAreaConfig = new DistrictAreaConfig();
//        BeanUtils.copyProperties(bindingDistrictAreaRequest, districtAreaConfig);
        int bindingResult = districtAreaConfigService.bindingDistrictArea(bindingDistrictAreaRequest.getAreaIds(),
                bindingDistrictAreaRequest.getDistrictId(),bindingDistrictAreaRequest.getHouseType());
        return NashResult.build(bindingResult);
    }

    /**
     * 解绑区县商圈
     * @param bindingDistrictAreaRequest
     * @return
     */
    @RequestMapping("/unbundling")
    @ResponseBody
    public NashResult unBundling(BindingDistrictAreaRequest bindingDistrictAreaRequest){

        logger.info("解绑区县商圈关系入参bindingDistrictAreaRequest:{}",bindingDistrictAreaRequest);
        DistrictAreaConfig districtAreaConfig = new DistrictAreaConfig();
//        BeanUtils.copyProperties(bindingDistrictAreaRequest, districtAreaConfig);
        int Unbundling = districtAreaConfigService.unBundlingDistrictArea(bindingDistrictAreaRequest.getAreaIds(),
                bindingDistrictAreaRequest.getDistrictId(),bindingDistrictAreaRequest.getHouseType());

        return NashResult.build(Unbundling);
    }

    /**
     * 根据商圈拼音首字母查询未绑定商圈
     * @param districtAreaConfigQuery
     * @return
     */
    @RequestMapping("/unbound")
    @ResponseBody
    public NashResult unbound(DistrictAreaConfigQuery districtAreaConfigQuery){

        logger.info("查询未绑定商圈pinyin:{}",districtAreaConfigQuery.getAreaPinyinInitials());

        List<Area> list = districtAreaConfigService.unBundlingAreaByPinyin(districtAreaConfigQuery);
        return NashResult.build(list);
    }


    /**
     * 城市，区域，商圈级联数据
     * @param districtAreaConfigQuery
     * @return
     */
    @RequestMapping(value= "/cascadecontrol",method = RequestMethod.POST)
    @ResponseBody
    public NashResult cascadeControl(DistrictAreaConfigQuery districtAreaConfigQuery){
        HashMap<String,Object> res=new HashMap<>();
        SerializableData serializableData = User.getCurrent().getSerializableData();
        districtAreaConfigQuery.setCityId(serializableData.getCityId());
        List<District> district=districtAreaConfigService.findDistrict(districtAreaConfigQuery.getCityId(),districtAreaConfigQuery.getHouseType());
        List<AreaWithDistrictID> area=districtAreaConfigService.findArea(districtAreaConfigQuery.getCityId(),districtAreaConfigQuery.getHouseType());
        res.put("district",district);
        res.put("area",area);
        return NashResult.build(res);
    }

}
