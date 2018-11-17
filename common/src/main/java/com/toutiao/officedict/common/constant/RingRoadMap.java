package com.toutiao.officedict.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 环路
 * @author WuShoulei on 2017/12/28
 */
public class RingRoadMap {

    private static final Map<Short, String> ringRoadMap;

    /**
     * 环路(1-二环以内,2-二至三环,3-三至四环,4-四至五环,5-五至六环,6-六环以外)
     */
    static {
        ringRoadMap = new HashMap<>();

        ringRoadMap.put((short)1, "二环以内");
        ringRoadMap.put((short)2, "二至三环");
        ringRoadMap.put((short)3, "三至四环");
        ringRoadMap.put((short)4, "四至五环");
        ringRoadMap.put((short)5, "五至六环");
        ringRoadMap.put((short)6, "六环以外");
    }

    public static String getRingRoadDesc(Short ringRoadCode) {

        String ringRoadDesc = ringRoadMap.get(ringRoadCode);

        if (null != ringRoadDesc) {
            return ringRoadMap.get(ringRoadCode);
        }

        return "";
    }
}
