package com.toutiao.officedict.apiimpl.controller;


import com.alibaba.fastjson.JSON;
import com.toutiao.officedict.api.chance.request.DistrictRequest;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.common.assertUtils.First;
import com.toutiao.officedict.common.assertUtils.Second;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.dao.entity.officedict.District;
import com.toutiao.officedict.domain.district.DistrictDomain;
import com.toutiao.officedict.domain.query.DistrictQuery;
import com.toutiao.officedict.service.district.DistrictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/district")
public class DistrictServeController {

    private Logger logger = LoggerFactory.getLogger(DistrictServeController.class);

    @Autowired
    private DistrictService districtService;

    /**
     * 获取区县列表
     * @param districtQuery
     * @return
     */
    @RequestMapping("/district-list")
    @ResponseBody
    public NashResult getDistrictList(DistrictQuery districtQuery){
        logger.info("查询区县列表入参district:{}",districtQuery);
        SerializableData serializableData = User.getCurrent().getSerializableData();
        districtQuery.setCurrCityId(serializableData.getCityId());
        List<DistrictDomain> dstrictList = districtService.getDistrictLists(districtQuery);
        logger.info("返回查询区县列表district:{}",dstrictList);
        return NashResult.build(dstrictList);

    }

    /**
     * 根据id删除区县
     * @param districtId
     * @return
     */
    @RequestMapping(value= "/del-district")
    @ResponseBody
    public NashResult delDistrictById(@RequestParam("id") Integer districtId){
        NashResult nashResult = new NashResult();
        logger.info("删除区县列表入参districtId:{}",districtId);
        int delResult =  districtService.delDistrict(districtId);
        if(delResult>0){
            nashResult = NashResult.build(delResult);
        }else {
            nashResult = NashResult.Fail("false","删除区县之前，请先解除对应的商圈绑定！",delResult);
        }
        return nashResult;
    }

    /**
     * 新增区县
     * @param districtRequest
     * @return
     */
    @RequestMapping(value= "/add-district", method = RequestMethod.POST)
    @ResponseBody
    public NashResult addDistrict(@Validated(First.class) DistrictRequest districtRequest){
        NashResult nashResult = new NashResult();
        logger.info("新增区县列表入参districtRequest:{}",districtRequest);
        District district = new District();
        BeanUtils.copyProperties(districtRequest, district);
        int addResult = districtService.addDistrict(district);
        if(addResult>0){
            nashResult = NashResult.build(addResult);
        }else if(addResult==-1){
            nashResult = NashResult.Fail("false","您输入的区县名称已经存在！",addResult);
        }
        return nashResult;
    }

    /**
     * 更新区县数据
     * @param districtRequest
     * @return
     */
    @RequestMapping(value= "/update-district",method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateDistrict(@Validated(Second.class) DistrictRequest districtRequest){
        logger.info("更新区县列表入参districtRequest:{}",districtRequest);
        District district = new District();
        BeanUtils.copyProperties(districtRequest, district);
        int updateResult = districtService.updateDistrict(district);
        return NashResult.build(updateResult);

    }

    /**
     * 根据id查询区县详情
     * @param districtId
     * @return
     */
    @RequestMapping(value= "/find-district",consumes = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    @ResponseBody
    public NashResult findDistrictDetails(@RequestBody Integer districtId){
        String result = "";
        logger.info("根据区县id查询详情districtId:{}", districtId);
        if(districtId != null){
            District district = districtService.findDistrictById(districtId);
            result = JSON.toJSONString(district);
            logger.info("根据区县id详情返回结果, result={}", result);
        }
        return NashResult.build(result);
    }

}
