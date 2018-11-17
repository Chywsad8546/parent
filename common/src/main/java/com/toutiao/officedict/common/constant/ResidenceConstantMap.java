package com.toutiao.officedict.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuShoulei on 2017/12/8
 */
public class ResidenceConstantMap {

    public static final Map<String, Integer> residenceCategoryFormMap;

    public static final Map<String, Integer> residenceBuildFormMap;

    public static final Map<String, Integer> residenceBuildCategoryMap;

    public static final Map<String, Integer> villaBuildStyleMap;

    static {
        residenceCategoryFormMap = new HashMap<>();
        residenceCategoryFormMap.put("普通住宅", 1);
        residenceCategoryFormMap.put("独栋", 6);
        residenceCategoryFormMap.put("联排", 7);
        residenceCategoryFormMap.put("花园洋房", 4);
    }

    static {
        residenceBuildFormMap = new HashMap<>();
        residenceBuildFormMap.put("低层", 1);
        residenceBuildFormMap.put("多层", 2);
        residenceBuildFormMap.put("小高层", 3);
        residenceBuildFormMap.put("高层", 4);
        residenceBuildFormMap.put("超高层", 5);
        residenceBuildFormMap.put("联排", 6);
        residenceBuildFormMap.put("独栋", 7);
        residenceBuildFormMap.put("双拼", 8);
        residenceBuildFormMap.put("叠拼", 9);
        residenceBuildFormMap.put("空中花园", 10);
        residenceBuildFormMap.put("空中别墅", 11);
        residenceBuildFormMap.put("大平层", 12);
    }

    static {
        residenceBuildCategoryMap = new HashMap<>();
        residenceBuildCategoryMap.put("板楼", 1);
        residenceBuildCategoryMap.put("塔楼", 2);
        residenceBuildCategoryMap.put("板塔结合", 3);
        residenceBuildCategoryMap.put("塔板结合", 3);
        residenceBuildCategoryMap.put("砖楼", 4);
    }

    static {
        villaBuildStyleMap = new HashMap<>();
        villaBuildStyleMap.put("中式", 1);
        villaBuildStyleMap.put("欧式", 2);
        villaBuildStyleMap.put("日式", 3);
        villaBuildStyleMap.put("美式", 4);
        villaBuildStyleMap.put("英式", 5);
        villaBuildStyleMap.put("澳式", 6);
        villaBuildStyleMap.put("法式", 7);
        villaBuildStyleMap.put("西班牙式", 8);
        villaBuildStyleMap.put("东南亚式", 9);
        villaBuildStyleMap.put("地中海式", 10);
        villaBuildStyleMap.put("意大利式", 11);
        villaBuildStyleMap.put("现代", 12);
    }

    public static Integer getResidenceCategoryId(String name) {
        Integer categoryId = residenceCategoryFormMap.get(name);

        if (categoryId != null) {
            return categoryId;
        }
        return null;
    }

    public static Integer getResidenceBuildFormId(String name) {
        Integer formId = residenceBuildFormMap.get(name);

        if (formId != null) {
            return formId;
        }
        return null;
    }

    public static Integer getResidenceBuildCategoryId(String name) {
        Integer categoryId = residenceBuildCategoryMap.get(name);

        if (categoryId != null) {
            return categoryId;
        }
        return null;
    }

    public static Integer getVillaBuildStyle(String name) {
        Integer styleId = villaBuildStyleMap.get(name);

        if (styleId != null) {
            return styleId;
        }
        return null;
    }

}