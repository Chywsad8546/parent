package com.toutiao.officedict.common.util.officedict;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuShoulei
 * @Date 2018/8/10 12:23
 */
public class CityCodeMap {

    private static final Map<String, Integer> cityCodeMap;

    static {
        cityCodeMap = new HashMap<>();
        cityCodeMap.put("bj", 12);
        cityCodeMap.put("sh", 13);
        cityCodeMap.put("tj", 14);
    }

    public static Integer getCityId(String city) {

        Integer cityId = cityCodeMap.get(city);

        if (null == cityId) {
            cityId = 12;
        }
        return cityId;
    }
}
