package com.toutiao.officedict.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 销售状态
 * @author WuShoulei on 2017/12/28
 */
public class SalesStatusMap {

    private static final Map<Short, String> salesStatusMap;

    /**
     * 销售状态(0-售完,1-在售,2-不在售,3-出租,4-租售,5-待售)
     */
    static {
        salesStatusMap = new HashMap<>();
        salesStatusMap.put((short)0, "售完");
        salesStatusMap.put((short)1, "在售");
        salesStatusMap.put((short)2, "不在售");
        salesStatusMap.put((short)3, "出租");
        salesStatusMap.put((short)4, "租售");
        salesStatusMap.put((short)5, "待售");
    }

    public static String getSalesStatusDesc(Short salesStatusCode) {

        String salesStatusDesc = salesStatusMap.get(salesStatusCode);

        if (null != salesStatusDesc) {
            return salesStatusMap.get(salesStatusCode);
        }

        return "";
    }
}
