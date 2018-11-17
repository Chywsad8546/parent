package com.toutiao.officedict.apiimpl.controller.relation;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.officedict.common.httpUtil.HmacUtils;
import com.toutiao.officedict.common.httpUtil.HttpUtils;
import com.toutiao.officedict.apiimpl.authentication.SerializableData;
import com.toutiao.officedict.apiimpl.authentication.User;
import com.toutiao.officedict.common.restmodel.NashResult;

import com.toutiao.officedict.dao.entity.officedict.RelationOfMaiTian;

import com.toutiao.officedict.domain.importDetail.MaiTianDetail;
import com.toutiao.officedict.domain.query.RelationOfMaiTianQuery;
import com.toutiao.officedict.service.relation.RelationOfMaiTianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 18710 on 2018/4/11.
 */
@RestController
@RequestMapping(value= "/maitian")
public class RelationOfMaiTianController {
    private Logger logger = LoggerFactory.getLogger(RelationOfMaiTianController.class);
    @Autowired
    private RelationOfMaiTianService relationOfMaiTianService;

    @Value("${maitian.importAddress}")
    public String importAddress;

    @Value("${maitian.importKey}")
    public String importKey;

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    /**
     * 获取关系列表
     * @param relationOfMaiTianQuery
     * @return
     */
    @RequestMapping(value = "/maitianList",method = RequestMethod.POST)
    @ResponseBody
    public NashResult getMaitianList(RelationOfMaiTianQuery relationOfMaiTianQuery){
//        User.getCurrent().getUserId()
        SerializableData serializableData = User.getCurrent().getSerializableData();
        relationOfMaiTianQuery.setCityId(serializableData.getCityId());
        List<RelationOfMaiTian> relaTionOfMaiTianQueryList = relationOfMaiTianService.selectRelationOfMaiTian(relationOfMaiTianQuery);
        return NashResult.build(relaTionOfMaiTianQueryList);
    }

    @RequestMapping(value = "/maitianOne",method = RequestMethod.POST)
    @ResponseBody
    public NashResult getMaitianOne(Integer id){
//        User.getCurrent().getUserId()
        RelationOfMaiTian relaTionOfMaiTianQueryList = relationOfMaiTianService.selectOneRelationOfMaiTian(id);
        return NashResult.build(relaTionOfMaiTianQueryList);
    }

    /**
     * 更新麦田-头条房产楼盘对应关系
     * @param relationOfMaiTianQuery
     * @return
     */
    @RequestMapping(value = "/updateMaiTian",method = RequestMethod.POST)
    @ResponseBody
    public NashResult updateMaiTian(RelationOfMaiTianQuery relationOfMaiTianQuery){
        Integer re= relationOfMaiTianService.updateById(relationOfMaiTianQuery);
        return NashResult.build(re);
    }

    /**
     * 添加麦田-头条房产对应关系
     * @param relationOfMaiTianQuery
     * @return
     */
    @RequestMapping(value = "/addMaiTian",method = RequestMethod.POST)
    @ResponseBody
    public NashResult addMaiTian(RelationOfMaiTianQuery relationOfMaiTianQuery){
        SerializableData serializableData = User.getCurrent().getSerializableData();
        relationOfMaiTianQuery.setCityId(serializableData.getCityId());
        Integer re= relationOfMaiTianService.saveMaitianMapping(relationOfMaiTianQuery);
        return NashResult.build(re);
    }

    @RequestMapping(value = "/touname",method = RequestMethod.POST)
    @ResponseBody
    public NashResult touname(String projname){
//        User.getCurrent().getUserId()
        Integer re= relationOfMaiTianService.selectByTouName(projname);
        return NashResult.build(re);
    }

    @RequestMapping(value = "/deleteMaiTian",method = RequestMethod.POST)
    @ResponseBody
    public NashResult deleteMaiTian(Integer id){
//        User.getCurrent().getUserId()
        Integer re= relationOfMaiTianService.deleteMaiTian(id);
        return NashResult.build(re);
    }

    @RequestMapping(value = "/importMaiTian",method = RequestMethod.POST)
    @ResponseBody
    public NashResult importMaiTian(String id){
        StringBuilder xiaoqu = HttpUtils.importPostHttpxiaoquMaitian("https://app.maitian.cn/service/housesecond",id);
        JSONObject data= JSON.parseObject(String.valueOf(xiaoqu));
        JSONArray list = data.getJSONObject("data").getJSONArray("List");
        if (list == null){
            return NashResult.build(0);
        }
        List<Object> res =new ArrayList();
        for (int i=0;i<list.size();i++) {
            JSONObject jsonObject1 = (JSONObject) list.get(i);
            MaiTianDetail maiTianDetail = new MaiTianDetail();
            String houseid=jsonObject1.getString("HouseCode");
            maiTianDetail.setFangyuanId(houseid);
            maiTianDetail.setLoupanId(jsonObject1.getString("CommunityID"));
            maiTianDetail.setProjName(jsonObject1.getString("CommunityName"));
            maiTianDetail.setDistrict(jsonObject1.getString("RegionName"));
            maiTianDetail.setCircle(jsonObject1.getString("CycleName"));
            //房源接口
            StringBuilder housedeatil = HttpUtils.importPostHttpMaitianDetail("https://app.maitian.cn/service/HouseSecond/Detail",houseid);

            //房源所有详情
            JSONObject jsonres = JSON.parseObject(housedeatil.toString()).getJSONObject("data");
            maiTianDetail.setHouseDesc(jsonres.getString("HouseDescription"));
            maiTianDetail.setHouseTitle(jsonres.getString("HouseTitle"));
            maiTianDetail.setAddress(jsonres.getJSONObject("CommunityInfo").getJSONObject("CommunityInfo").getString("Address"));
            maiTianDetail.setYear(jsonres.getJSONObject("CommunityInfo").getJSONObject("CommunityInfo").getString("BuildYear"));


            //房源详情
            JSONObject houseInfo = jsonres.getJSONObject("HouseInfo");
            maiTianDetail.setBuildArea(houseInfo.getDouble("AreaSize"));
           if(houseInfo.getString("PriceTotalUnit").equals("亿元")){
                maiTianDetail.setPrice(houseInfo.getDouble("PriceTotal")*10000);
            }else{
               maiTianDetail.setPrice(houseInfo.getDouble("PriceTotal"));
            }
            maiTianDetail.setAccessory(houseInfo.getString("FollowInfo"));
            JSONArray tags = houseInfo.getJSONArray("DisplayTag");
            JSONArray tagsName =new JSONArray();
            if (tags != null){
                for (int t=0;t<tags.size();t++){
                    String tagname = tags.getJSONObject(t).getString("TagName");
                    if (tagname.equals("随时可看")){
                           tagsName.add(3);
                    }else if (tagname.equals("满五")){
                        tagsName.add(4);
                    }else if (tagname.equals("带车位")){
                        tagsName.add(20);
                    }else if (tagname.contains("线")){
                        tagsName.add(1);
                    }

                    if (tagsName.size() != 0){
                        Integer tagsString[] =new Integer[tagsName.size()];
                        for(int y=0;y<tagsName.size();y++){
                            tagsString[y]=tagsName.getInteger(y);
                        }
                        maiTianDetail.setTags(tagsString);
                    }
                }
            }


            String heading =houseInfo.getString("Direction");
            if (heading.equals("东")){
                maiTianDetail.setForward(1);
            }else if (heading.equals("西")){
                maiTianDetail.setForward(2);
            }else if (heading.equals("南")){
                maiTianDetail.setForward(3);
            }else if (heading.equals("北")){
                maiTianDetail.setForward(4);
            }else if (heading.equals("东南")||heading.equals("南东")){
                maiTianDetail.setForward(5);
            }else if (heading.equals("西南")||heading.equals("南西")){
                maiTianDetail.setForward(6);
            }else if (heading.equals("东北")||heading.equals("北东")){
                maiTianDetail.setForward(7);
            }else if (heading.equals("西北")||heading.equals("北西")){
                maiTianDetail.setForward(8);
            }else if (heading.equals("东西")||heading.equals("西东")){
                maiTianDetail.setForward(9);
            }else if (heading.equals("南北")||heading.equals("北南")){
                maiTianDetail.setForward(10);
            }else if (heading.equals("南东北")||heading.equals("南北东")||heading.equals("东南北")||heading.equals("东北南")||heading.equals("北东南")||heading.equals("北南东")){
                maiTianDetail.setForward(11);
            }else if (heading.equals("南西北")||heading.equals("南北西")||heading.equals("北南西")||heading.equals("北西南")||heading.equals("西北南")||heading.equals("西南北")){
                maiTianDetail.setForward(12);
            }else if (heading.equals("东南西")||heading.equals("东西南")||heading.equals("南东西")||heading.equals("南西东")||heading.equals("西南东")||heading.equals("西东南")){
                maiTianDetail.setForward(13);
            }else if (heading.equals("东北西")||heading.equals("东西北")||heading.equals("西东北")||heading.equals("西北东")||heading.equals("北西东")||heading.equals("北东西")){
                maiTianDetail.setForward(14);
            }else if (heading.length()==4){
                maiTianDetail.setForward(15);
            }

            if (houseInfo.getJSONArray("Layout").size()>= 5){
                maiTianDetail.setRoom(houseInfo.getJSONArray("Layout").getInteger(0));
                maiTianDetail.setHall(houseInfo.getJSONArray("Layout").getInteger(1));
                maiTianDetail.setKitchen(houseInfo.getJSONArray("Layout").getInteger(2));
                maiTianDetail.setToilet(houseInfo.getJSONArray("Layout").getInteger(3));
                maiTianDetail.setBalcony(houseInfo.getJSONArray("Layout").getInteger(4));
            }

            String buildForm =houseInfo.getString("Floor");
            buildForm=buildForm.trim();
            String str2="";
            if(!"".equals(buildForm)){
                for(int b=0;b<buildForm.length();b++){
                    if(buildForm.charAt(b)>=48 && buildForm.charAt(b)<=57){
                        str2+=buildForm.charAt(b);
                    }
                }
            }

            maiTianDetail.setTotalFloor(Integer.parseInt(str2));
            maiTianDetail.setFloor(buildForm.substring(0,1));

            maiTianDetail.setLogoPic("http://img.maitian.cn/"+jsonres.getString("DefaultPic").replace("212X157","800X600"));


            ArrayList<String> shineiImgs = new ArrayList<String>();
            ArrayList<String> layoutImgs =new ArrayList<String>();

            JSONArray pics = jsonres.getJSONArray("HousePicList");
            if (pics != null){
                    for (int p=0;p<pics.size();p++){
                        JSONObject picItem = pics.getJSONObject(p);
                        if (picItem.getString("PicType").equals("户型")){
                            layoutImgs.add("http://img.maitian.cn/"+picItem.getString("PicFullUrl"));
                        }else {
                            shineiImgs.add("http://img.maitian.cn/"+picItem.getString("PicFullUrl"));
                        }
                }
            }

            String[] shineiAimgs = shineiImgs.toArray(new String[shineiImgs.size()]);
            String[] layoutAimgs = layoutImgs.toArray(new String[layoutImgs.size()]);
            maiTianDetail.setLayoutPic(layoutAimgs);
            maiTianDetail.setRoomPic(shineiAimgs);

            JSONArray agent =jsonres.getJSONArray("CommentList");
            if (agent!=null){
                JSONObject agentD = agent.getJSONObject(0).getJSONObject("CommentBroker").getJSONObject("BrokerInfo");
                maiTianDetail.setAgentName(agentD.getString("BrokerName"));
                maiTianDetail.setAgentTel(agentD.getString("BrokerPhone"));
                maiTianDetail.setAgentPic("http://img.maitian.cn/"+agentD.getString("BrokerPic"));
                if (maiTianDetail.getHouseDesc()==null || maiTianDetail.getHouseDesc().equals("")){
                       maiTianDetail.setHouseDesc(agent.getJSONObject(0).getString("CommentContent"));
                }
            }


            String json = JSON.toJSONString(maiTianDetail);

            String singn = HmacUtils.s(""+json,importKey);

            String status = JSON.parseObject(HttpUtils.postJson(importAddress,json,"utf-8",singn)).getString("status");

            if (!status.equals("200")){
                return NashResult.build("1");
            }else if (status.equals("200")){
                res.add("0");
            }
        }
        return NashResult.build(res);
    }

}
