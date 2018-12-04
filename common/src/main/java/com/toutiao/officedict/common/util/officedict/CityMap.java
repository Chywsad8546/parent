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
        cityMap.put(1, "石家庄");
        cityMap.put(12, "北京");
        cityMap.put(13, "上海");
        cityMap.put(14, "天津");
        cityMap.put(15, "重庆");
        cityMap.put(16, "广州");
        cityMap.put(17, "深圳");
        cityMap.put(18, "珠海");
        cityMap.put(23, "惠州");
        cityMap.put(24, "东莞");
        cityMap.put(26, "杭州");
        cityMap.put(45, "成都");
        cityMap.put(55, "沈阳");
        cityMap.put(56, "大连");
        cityMap.put(65, "南京");
        cityMap.put(66, "无锡");
        cityMap.put(67, "苏州");
        cityMap.put(75, "福州");
        cityMap.put(84, "长春");
        cityMap.put(103, "郑州");
        cityMap.put(113, "济南");
        cityMap.put(114, "青岛");
        cityMap.put(118, "烟台");
        cityMap.put(123, "合肥");
        cityMap.put(143, "海口");
        cityMap.put(144, "三亚");
        cityMap.put(176, "西安");
        cityMap.put(194, "武汉");
        cityMap.put(204, "长沙");
        cityMap.put(214, "南昌");
    }

    public static String getCityCNName(Integer cityId) {

        String cnName = cityMap.get(cityId);

        if (null == cityId) {
            return DEFAULT_CITY;
        }
        return cnName;
    }
}
