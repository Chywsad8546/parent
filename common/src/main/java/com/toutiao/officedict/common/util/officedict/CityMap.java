package com.toutiao.officedict.common.util.officedict;

import java.util.HashMap;
import java.util.Map;

/**
 * 城市Map
 * @author WuShoulei on 2017/12/25
 */
public class CityMap {

    private static final Map<Integer, String> cityMap;

    /**
     * 默认城市
     */
    private static final String DEFAULT_CITY = "北京";

    static {
        cityMap = new HashMap<>();
        cityMap.put(12, "北京");
        cityMap.put(13, "上海");
        cityMap.put(14, "天津");
    }

    public static String getCityCNName(Integer cityId) {

        String cnName = cityMap.get(cityId);

        if (null == cityId) {
            return DEFAULT_CITY;
        }
        return cnName;
    }
}
