package com.toutiao.officedict.apiimpl.controller.relation;


import com.toutiao.officedict.api.chance.request.mapping.NotMatchRequest;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.common.constant.OfficeDictConstant;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.domain.mapping.NotMatchModel;
import com.toutiao.officedict.domain.query.mapping.NotMatchQuery;
import com.toutiao.officedict.service.mapping.NoMatchMappingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 楼盘未匹配处理
 * @author WuShoulei on 2018/7/16
 */
@RequestMapping("/mapping")
@RestController
public class NotMatchMappingController {

    @Autowired
    private NoMatchMappingService noMatchMappingService;

    @RequestMapping(value = "/listNotMatchMappings")
    public NashResult listNotMatchMappings(NotMatchRequest notMatchRequest) {
        SerializableData serializableData = User.getCurrent().getSerializableData();

        if (null == notMatchRequest.getCityId()) {
            notMatchRequest.setCityId(serializableData.getCityId());
        }

        NotMatchQuery notMatchQuery = new NotMatchQuery();
        BeanUtils.copyProperties(notMatchRequest, notMatchQuery);

        List<NotMatchModel> notMatchModelList = noMatchMappingService.listNotMatchMappings(notMatchQuery);

        return NashResult.build(notMatchModelList);
    }

    @RequestMapping(value = "/selectNotMatch",method = RequestMethod.POST)
    @ResponseBody
    public NashResult getWoAiList(Integer id){
//        User.getCurrent().getUserId()
        NotMatchModel notMatchList  = noMatchMappingService.selectNotMatch(id);
        return NashResult.build(notMatchList);
    }


    @RequestMapping(value = "/updateStutas",method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateStutas(NotMatchRequest notMatchRequest){
//        User.getCurrent().getUserId()
        NotMatchQuery notMatchQuery = new NotMatchQuery();
        BeanUtils.copyProperties(notMatchRequest, notMatchQuery);
        Integer re = noMatchMappingService.updateStutas(notMatchQuery);
        return NashResult.build(re);
    }

    @RequestMapping(value = "/deleteRele",method = RequestMethod.POST)
    @ResponseBody
    public NashResult deleteRele(Integer id){
//        User.getCurrent().getUserId()
        Integer re = noMatchMappingService.deleteRela(id);
        return NashResult.build(re);
    }

    @RequestMapping(value = "/getCompanys",method = RequestMethod.POST)
    @ResponseBody
    public NashResult getCompanys(){
//        User.getCurrent().getUserId()
        List<String> re = noMatchMappingService.getCompanys();
        return NashResult.build(re);
    }
}
