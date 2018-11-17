$.namespace("com.toutiao.officedict.activityinfo.activityList");
com.toutiao.officedict.activityinfo.activityList=new com.toutiao.core.api.listApi('/activityInfo/activityList','POST',{});


$.namespace("com.toutiao.officedict.activityinfo.oneActivityInfo");
com.toutiao.officedict.activityinfo.oneActivityInfo=new com.toutiao.core.api.singleApi('/activityInfo/oneActivityInfo','POST',{});

$.namespace("com.toutiao.officedict.activityinfo.addActivity");
com.toutiao.officedict.activityinfo.addActivity=new com.toutiao.core.api.singleApi('/activityInfo/addActivity','POST',{});


$.namespace("com.toutiao.officedict.activityinfo.updateById");
com.toutiao.officedict.activityinfo.updateById=new com.toutiao.core.api.singleApi('/activityInfo/updateById','POST',{});

$.namespace("com.toutiao.officedict.activityinfo.removeActivityInfo");
com.toutiao.officedict.activityinfo.removeActivityInfo=new com.toutiao.core.api.singleApi('/activityInfo/removeActivityInfo','POST',{});

/*
 * 是否开启新房楼盘活动
 * */
$.namespace("com.toutiao.officedict.project.updateIsAcitivity");
com.toutiao.officedict.project.updateIsAcitivity=new com.toutiao.core.api.singleApi('/newHouse/updateIsAcitivity','POST',{});