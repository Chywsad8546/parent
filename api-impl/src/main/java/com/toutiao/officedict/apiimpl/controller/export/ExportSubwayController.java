package com.toutiao.officedict.apiimpl.controller.export;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.toutiao.officedict.dao.entity.officedict.ExportSubway;
import com.toutiao.officedict.service.subway.ExportSubwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by 18710 on 2018/9/25.
 */
@RestController
public class ExportSubwayController {
    @Autowired
    ExportSubwayService exportSubwayService;

    @RequestMapping(value = "/exportSubway", method = RequestMethod.POST)
    @ResponseBody
    public Object exportSubway(Integer cityId) {
        boolean flag =true;
        ArrayList<ExportSubway> re= exportSubwayService.exportSubwayList(cityId);
        JSONArray relist = new JSONArray();
        Integer lineId= re.get(0).getLineId();
        JSONObject first = new JSONObject();
        JSONArray children = new JSONArray();
       for (int i=0;i<re.size();i++){
           ExportSubway it = re.get(i);
           JSONObject two =new JSONObject();

           if (i==re.size()-1){
               first.put("name",re.get(i).getSubwayName());
               first.put("subwayid",re.get(i).getLineId());
               two.put("station_name",it.getStationName());
               two.put("stationid",it.getStationId());
               children.add(two.clone());
               first.put("children",children.clone());
               relist.add(first.clone());
           }else if (!it.getLineId().equals(lineId)){
               first.put("name",re.get(i-1).getSubwayName());
               first.put("subwayid",re.get(i-1).getLineId());
               first.put("children",children.clone());
               relist.add(first.clone());
          /*     JSONArray childrenT = (JSONArray) children.clone();
               JSONObject firstT =new JSONObject();
               childrenT.clone();*/
               lineId = it.getLineId();
               children.clear();
               first.clear();
           }
           two.put("station_name",it.getStationName());
           two.put("stationid",it.getStationId());
           children.add(two.clone());
       }
   /*      return relist.toString();*/
// 拼接文件完整路径
        String fullPath = "E:\\subways.json";

        // 生成json格式文件
        try {
            // 保证创建一个新文件
            File file = new File(fullPath);
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();

            // 格式化json字符串
           String  jsonString = relist.toString();

            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(jsonString);
            write.flush();
            write.close();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
        }

        // 返回是否成功的标记
        return flag;
    }

}
