package com.toutiao.officedict.common.staticonst;

import lombok.Data;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by 18710 on 2018/12/4.
 */
@Data
public class City {
   private Integer cityId;
   private String cityName;
   private String cityAbbreviation;

   public City(Integer cityId,String cityName,String cityAbbreviation){
      this.cityId = cityId;
      this.cityName = cityName;
      this.cityAbbreviation = cityAbbreviation;
   }
}
