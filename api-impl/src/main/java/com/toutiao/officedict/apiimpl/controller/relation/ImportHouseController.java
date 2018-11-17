package com.toutiao.officedict.apiimpl.controller.relation;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.officedict.common.httpUtil.HmacUtils;
import com.toutiao.officedict.common.httpUtil.HttpUtils;
import com.toutiao.officedict.common.restmodel.NashResult;
import com.toutiao.officedict.domain.importDetail.MaiTianDetail;
import com.toutiao.officedict.domain.importDetail.WoaiDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 18710 on 2018/9/19.
 */
@RequestMapping("/import")
@RestController
public class ImportHouseController {
    private Logger logger = LoggerFactory.getLogger(RelationOfMaiTianController.class);

    @Value("${woai.importAddress}")
    public String woaiimportAddress;

    @Value("${woai.importKey}")
    public String woaiimportKey;

    @Value("${maitian.importAddress}")
    public String maitianimportAddress;

    @Value("${maitian.importKey}")
    public String maitianimportKey;


    @RequestMapping(value = "/importWoai",method = RequestMethod.POST)
    @ResponseBody
    public NashResult importWoai(Integer id){
        StringBuilder xiaoqu = HttpUtils.importPostHttpxiaoqu("https://appapi.5i5j.com/appapi/exchange/1/v1/prolist",id);
        JSONObject data= JSON.parseObject(String.valueOf(xiaoqu));
        JSONObject datalist =data.getJSONObject("data");
        JSONArray list = datalist.getJSONArray("list");
        List<Object> res =new ArrayList();
        for (int i=0;i<list.size();i++){
            if(i==14){
                break;
            }
            JSONObject jsonObject1 = (JSONObject) list.get(i);
            Integer houseid = jsonObject1.getInteger("housesid");
            StringBuilder housedeatil = HttpUtils.importPostHttpHouseDetail("https://appapi.5i5j.com/appapi/exchange/1/v1/allinfo",houseid);
            JSONObject jsonres = JSON.parseObject(housedeatil.toString());
            JSONObject jsonhouseres = jsonres.getJSONObject("data").getJSONObject("houseinfo");
            JSONObject jsonxqres = jsonres.getJSONObject("data").getJSONObject("community");
            WoaiDetail woaiDetail =new WoaiDetail();
            woaiDetail.setFangyuanId(jsonhouseres.getString("housesid"));
            woaiDetail.setHouseTitle(jsonhouseres.getString("housetitle"));
            String houseDescit="";
            if(jsonhouseres.getString("memo")!=null){
                houseDescit+=jsonhouseres.getString("memo");
            }

            if (jsonhouseres.getString("memo1")!=null){
                houseDescit+="\\r\\n";
                houseDescit+=jsonhouseres.getString("memo1");
            }

            if (jsonhouseres.getString("memo2")!=null){
                houseDescit+="\\r\\n";
                houseDescit+=jsonhouseres.getString("memo2");
            }

            if (jsonhouseres.getString("memo5")!=null){
                houseDescit+="\\r\\n";
                houseDescit+=jsonhouseres.getString("memo5");
            }

            if (jsonhouseres.getString("memo4")!=null){
                houseDescit+="\\r\\n";
                houseDescit+=jsonhouseres.getString("memo4");
            }
            woaiDetail.setHouseDesc(houseDescit);
            woaiDetail.setBuildArea(jsonhouseres.getDouble("buildarea"));
            woaiDetail.setLoupanId(jsonxqres.getString("communityid"));
            woaiDetail.setProjName(jsonhouseres.getString("communityname"));
            woaiDetail.setAddress(jsonres.getJSONObject("data").getJSONObject("communityaround").getString("selladd"));
            woaiDetail.setDistrict(jsonxqres.getString("qyname"));
            woaiDetail.setCircle(jsonxqres.getString("sqname"));
            woaiDetail.setYear(jsonhouseres.getString("buildyear"));
            String looktime =jsonhouseres.getString("looktime");
            if (looktime.equals("随时看房") ||looktime.equals("时间不限")){
                woaiDetail.setLookTime(1);
            }else if (looktime.equals("周一到周五")){
                woaiDetail.setLookTime(2);
            }else if (looktime.equals("周六日")){
                woaiDetail.setLookTime(3);
            }else if (looktime.equals("电话预约")){
                woaiDetail.setLookTime(4);
            }
            woaiDetail.setRoom(jsonhouseres.getInteger("bedroom"));
            woaiDetail.setHall(jsonhouseres.getInteger("livingroom"));
            woaiDetail.setToilet(jsonhouseres.getInteger("toilet"));

            woaiDetail.setTotal_floor(jsonhouseres.getInteger("houseallfloor"));
            woaiDetail.setFloorNo(jsonhouseres.getInteger("buildingfloor"));


            String fitment =jsonhouseres.getString("decoratelevel");
            if (fitment.equals("毛坯")){
                woaiDetail.setFitment(1);
            }else if (fitment.equals("普通装修")||fitment.equals("简装")){
                woaiDetail.setFitment(2);
            }else if (fitment.equals("精装修")||fitment.equals("精装")){
                woaiDetail.setFitment(3);
            }else if (fitment.equals("豪华装修")||fitment.equals("豪装")){
                woaiDetail.setFitment(4);
            }else if (fitment.equals("其他")){
                woaiDetail.setFitment(5);
            }else if (fitment.equals("非毛坯")){
                woaiDetail.setFitment(6);
            }else if(fitment.equals("公共部分简单装修")){
                woaiDetail.setFitment(7);
            }else if(fitment.equals("中装")){
                woaiDetail.setFitment(8);
            }else {
                woaiDetail.setFitment(5);
            }

            String category =jsonhouseres.getString("floortype");
            if (category.equals("板楼")){
                woaiDetail.setBuildCategory(1);
            }else if (category.equals("塔楼")){
                woaiDetail.setBuildCategory(2);
            }else if (category.equals("板塔结合")){
                woaiDetail.setBuildCategory(3);
            }else if (category.equals("砖楼")){
                woaiDetail.setBuildCategory(4);
            }else {
                woaiDetail.setBuildCategory(5);
            }

            String buildForm =jsonhouseres.getString("floorStr");
            String[] strs=buildForm.split("/");
            if (strs.length>0){
                String buildForm1 = strs[0];
                if (buildForm1.equals("低层")){
                    woaiDetail.setBuildForm(1);
                }else if (buildForm1.equals("多层")){
                    woaiDetail.setBuildForm(2);
                }else if (buildForm1.equals("小高层")){
                    woaiDetail.setBuildForm(3);
                }else if (buildForm1.equals("高层")){
                    woaiDetail.setBuildForm(4);
                }else if (buildForm1.equals("超高层")){
                    woaiDetail.setBuildForm(5);
                }else if (buildForm1.equals("联排")){
                    woaiDetail.setBuildForm(6);
                }else if (buildForm1.equals("独栋")){
                    woaiDetail.setBuildForm(7);
                }else if (buildForm1.equals("双拼")){
                    woaiDetail.setBuildForm(8);
                }else if (buildForm1.equals("叠拼")){
                    woaiDetail.setBuildForm(9);
                }else if (buildForm1.equals("空中花园")){
                    woaiDetail.setBuildForm(10);
                }else if (buildForm1.equals("空中别墅")){
                    woaiDetail.setBuildForm(11);
                }else if (buildForm1.equals("开间")){
                    woaiDetail.setBuildForm(12);
                }else if (buildForm1.equals("平层")){
                    woaiDetail.setBuildForm(13);
                }else if (buildForm1.equals("复式") ){
                    woaiDetail.setBuildForm(14);
                }else if (buildForm1.equals("跃层") ){
                    woaiDetail.setBuildForm(15);
                }else {
                    woaiDetail.setBuildForm(16);
                }
            }

            JSONArray tags=jsonhouseres.getJSONArray("tagwall");
            if (tags!=null){
                String tagsString[] =new String[tags.size()];
                for(int y=0;y<tags.size();y++){
                    tagsString[y]=tags.getString(y);
                }
                woaiDetail.setTagStrArray(tagsString);
            }



            String housetype =jsonhouseres.getString("housetype");
            if (housetype.equals("普通住宅")){
                woaiDetail.setHouseType(1);
            }else if (housetype.equals("公寓")){
                woaiDetail.setHouseType(2);
            }else if (housetype.equals("酒店式公寓")){
                woaiDetail.setHouseType(3);
            }else if (housetype.equals("花园洋房")){
                woaiDetail.setHouseType(4);
            }else if (housetype.equals("商住楼")){
                woaiDetail.setHouseType(5);
            }else if (housetype.equals("独栋别墅")){
                woaiDetail.setHouseType(6);
            }else if (housetype.equals("联排别墅")){
                woaiDetail.setHouseType(7);
            }else if (housetype.equals("经济适用房")){
                woaiDetail.setHouseType(8);
            }else if (housetype.equals("廉租房")){
                woaiDetail.setHouseType(9);
            }

            JSONArray webHousePlList = jsonhouseres.getJSONArray("webHousePlList");
            if (webHousePlList != null){
                woaiDetail.setAgentName(webHousePlList.getJSONObject(0).getString("username"));
                woaiDetail.setAgentTel(webHousePlList.getJSONObject(0).getString("handset"));
                woaiDetail.setAgentPic(webHousePlList.getJSONObject(0).getString("header"));

    /*          String time=webHousePlList.getJSONObject(0).getString("dktime");
       *//*       SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
              Date date = null;
              try {
                  date = format.parse(time);
              } catch (ParseException e) {
                  e.printStackTrace();
              }
              woaiDetail.setUpdateTime((int) date.getTime());*//*
              woaiDetail.setUpdateTime(Integer.parseInt(time));*/
            }

            woaiDetail.setPrice(jsonhouseres.getDouble("price"));
            woaiDetail.setAccessory(jsonhouseres.getString("rim"));
            woaiDetail.setTraffic(jsonhouseres.getString("traffic"));

            String heading =jsonhouseres.getString("heading");
            if (heading.equals("东")){
                woaiDetail.setForward(1);
            }else if (heading.equals("西")){
                woaiDetail.setForward(2);
            }else if (heading.equals("南")){
                woaiDetail.setForward(3);
            }else if (heading.equals("北")){
                woaiDetail.setForward(4);
            }else if (heading.equals("东南")||heading.equals("南东")){
                woaiDetail.setForward(5);
            }else if (heading.equals("西南")||heading.equals("南西")){
                woaiDetail.setForward(6);
            }else if (heading.equals("东北")||heading.equals("北东")){
                woaiDetail.setForward(7);
            }else if (heading.equals("西北")||heading.equals("北西")){
                woaiDetail.setForward(8);
            }else if (heading.equals("东西")||heading.equals("西东")){
                woaiDetail.setForward(9);
            }else if (heading.equals("南北")||heading.equals("北南")){
                woaiDetail.setForward(10);
            }else if (heading.equals("南东北")||heading.equals("南北东")||heading.equals("东南北")||heading.equals("东北南")||heading.equals("北东南")||heading.equals("北南东")){
                woaiDetail.setForward(11);
            }else if (heading.equals("南西北")||heading.equals("南北西")||heading.equals("北南西")||heading.equals("北西南")||heading.equals("西北南")||heading.equals("西南北")){
                woaiDetail.setForward(12);
            }else if (heading.equals("东南西")||heading.equals("东西南")||heading.equals("南东西")||heading.equals("南西东")||heading.equals("西南东")||heading.equals("西东南")){
                woaiDetail.setForward(13);
            }else if (heading.equals("东北西")||heading.equals("东西北")||heading.equals("西东北")||heading.equals("西北东")||heading.equals("北西东")||heading.equals("北东西")){
                woaiDetail.setForward(14);
            }else if (heading.length()==4){
                woaiDetail.setForward(15);
            }

            String rightprop =jsonhouseres.getString("rightprop");
            if (rightprop.equals("已购公房")){
                woaiDetail.setPropertyRight(1);
            }else if (rightprop.equals("商品房")){
                woaiDetail.setPropertyRight(2);
            }else if (rightprop.equals("空置房")){
                woaiDetail.setPropertyRight(3);
            }else if (rightprop.equals("经济适用房")){
                woaiDetail.setPropertyRight(4);
            }else if (rightprop.equals("使用权房")){
                woaiDetail.setPropertyRight(5);
            }else if (rightprop.equals("央产")){
                woaiDetail.setPropertyRight(6);
            }else if (rightprop.equals("按经济试用住房管理的房屋")){
                woaiDetail.setPropertyRight(7);
            }else if (rightprop.equals("其他")){
                woaiDetail.setPropertyRight(8);
            }/*else if (rightprop.equals("经济适用房")){
              woaiDetail.setPropertyRight(9);
          }*/else if (rightprop.equals("公共租赁房")){
                woaiDetail.setPropertyRight(10);
            }else if (rightprop.equals("定向安置房")){
                woaiDetail.setPropertyRight(11);
            }else if (rightprop.equals("两限商品房")){
                woaiDetail.setPropertyRight(12);
            }else if (rightprop.equals("自住型商品房")){
                woaiDetail.setPropertyRight(13);
            }else if (rightprop.equals("其他")){
                woaiDetail.setPropertyRight(14);
            }else if (rightprop.equals("商铺")){
                woaiDetail.setPropertyRight(15);
            }else if (rightprop.equals("写字楼")){
                woaiDetail.setPropertyRight(16);
            }else if (rightprop.equals("平房")){
                woaiDetail.setPropertyRight(17);
            }else if (rightprop.equals("车位")){
                woaiDetail.setPropertyRight(18);
            }else if (rightprop.equals("办公")){
                woaiDetail.setPropertyRight(19);
            }else if (rightprop.equals("四合院")){
                woaiDetail.setPropertyRight(20);
            }else if (rightprop.equals("住宅商铺")){
                woaiDetail.setPropertyRight(21);
            }else if (rightprop.equals("写字楼商铺")){
                woaiDetail.setPropertyRight(22);
            }else if (rightprop.equals("商务型商铺")){
                woaiDetail.setPropertyRight(23);
            }else if (rightprop.equals("市场类商铺")){
                woaiDetail.setPropertyRight(24);
            }else if (rightprop.equals("标准写字楼")){
                woaiDetail.setPropertyRight(25);
            }else if (rightprop.equals("酒店写字楼")){
                woaiDetail.setPropertyRight(26);
            }else if (rightprop.equals("别墅")){
                woaiDetail.setPropertyRight(27);
            }else if (rightprop.equals("办公别墅")){
                woaiDetail.setPropertyRight(28);
            }else if (rightprop.equals("购物中心") ){
                woaiDetail.setPropertyRight(29);
            }else if (rightprop.equals("商业")){
                woaiDetail.setPropertyRight(30);
            }else if (rightprop.equals("企业独栋")){
                woaiDetail.setPropertyRight(31);
            }else if (rightprop.equals("住宅底商")){
                woaiDetail.setPropertyRight(32);
            }else if (rightprop.equals("自持租赁")){
                woaiDetail.setPropertyRight(33);
            }else if (rightprop.equals("总部园区")){
                woaiDetail.setPropertyRight(34);
            }else if (rightprop.equals("工业厂房")){
                woaiDetail.setPropertyRight(35);
            }else if (rightprop.equals("车库")){
                woaiDetail.setPropertyRight(36);
            }

            JSONArray imgsa=jsonhouseres.getJSONArray("imgs");
            ArrayList<String> shineiImgs = new ArrayList<String>();
            ArrayList<String> layoutImgs =new ArrayList<String>();
            ArrayList<String> shiwaiImgs =new ArrayList<String>();
            if (imgsa!=null){
                for (int imgi=0;imgi<imgsa.size();imgi++){
                    String img = imgsa.getString(imgi);
                    String [] imgA = img.split("/") ;
                    if (imgA[7].equals("shinei")){
                        shineiImgs.add(img);
                    }else if(imgA[7].equals("huxing")){
                        layoutImgs.add(img);
                    }else if(imgA[7].equals("shiwai")){
                        shiwaiImgs.add(img);
                    }
                }
            }

            String[] shineiAimgs = shineiImgs.toArray(new String[shineiImgs.size()]);
            String[] layoutAimgs = layoutImgs.toArray(new String[layoutImgs.size()]);
            String[] shiwaiAimgs = shiwaiImgs.toArray(new String[shiwaiImgs.size()]);

            woaiDetail.setRoomPic(shineiAimgs);
            woaiDetail.setLayoutPic(layoutAimgs);
            woaiDetail.setOutPic(shiwaiAimgs);

            woaiDetail.setLogoPic(jsonhouseres.getString("imgurl").replace("p7","p5"));

            String json = JSON.toJSONString(woaiDetail);

            String singn = HmacUtils.s(""+json,woaiimportKey);
            String status = JSON.parseObject(HttpUtils.postJson(woaiimportAddress,json,"utf-8",singn)).getString("status");

            if (!status.equals("200")){
                return NashResult.build("1");
            }else if (status.equals("200")){
                res.add("0");

            }


        }
        return NashResult.build("0");
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

            String singn = HmacUtils.s(""+json,maitianimportKey);

            String status = JSON.parseObject(HttpUtils.postJson(maitianimportAddress,json,"utf-8",singn)).getString("status");

            if (!status.equals("200")){
                return NashResult.build("1");
            }else if (status.equals("200")){
                res.add("0");
            }
        }
        return NashResult.build(res);
    }

}
