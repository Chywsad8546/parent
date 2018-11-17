/**
 * Created by 18710 on 2018/4/17.
 */
$.namespace("com.toutiao.officedict.dynamicInfo.dynamicList");
com.toutiao.officedict.dynamicInfo.dynamicList=new com.toutiao.core.api.listApi('/dynamicInfo/dynamicInfoList','POST',{});


$.namespace("com.toutiao.officedict.dynamicInfo.addDynamicInfo");
com.toutiao.officedict.dynamicInfo.addDynamicInfo=new com.toutiao.core.api.singleApi('/dynamicInfo/addDynamicInfo','POST',{});


$.namespace("com.toutiao.officedict.dynamicInfo.removeDynamicInfo");
com.toutiao.officedict.dynamicInfo.removeDynamicInfo=new com.toutiao.core.api.singleApi('/dynamicInfo/removeDynamicInfo','GET',{});


$.namespace("com.toutiao.officedict.dynamicInfo.oneDynamicInfo");
com.toutiao.officedict.dynamicInfo.oneDynamicInfo=new com.toutiao.core.api.singleApi('/dynamicInfo/oneDynamicInfo','GET',{});


$.namespace("com.toutiao.officedict.dynamicInfo.updateById");
com.toutiao.officedict.dynamicInfo.updateById=new com.toutiao.core.api.singleApi('/dynamicInfo/updateById','POST',{});
