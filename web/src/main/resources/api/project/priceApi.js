/*
*  新房楼盘价格管理
* */
$.namespace("com.toutiao.officedict.price.listProjectPrice");
com.toutiao.officedict.price.listProjectPrice=new com.toutiao.core.api.listApi('/price/listProjectPrice','GET',{});

/**
 * 添加新房楼盘价格信息
 */
$.namespace("com.toutiao.officedict.price.saveProjectPrice");
com.toutiao.officedict.price.saveProjectPrice=new com.toutiao.core.api.singleApi('/price/saveProjectPrice','POST',{});

/**
 * 查看某个价格信息
 */
$.namespace("com.toutiao.officedict.price.getProjectPrice");
com.toutiao.officedict.price.getProjectPrice=new com.toutiao.core.api.singleApi('/price/getProjectPrice','GET',{});

/**
 * 删除某个价格信息
 */
$.namespace("com.toutiao.officedict.price.deleteProjectPrice");
com.toutiao.officedict.price.deleteProjectPrice=new com.toutiao.core.api.singleApi('/price/deleteProjectPrice','GET',{});

/**
 * 更新某个价格信息
 */
$.namespace("com.toutiao.officedict.price.updateProjectPrice");
com.toutiao.officedict.price.updateProjectPrice=new com.toutiao.core.api.singleApi('/price/updateProjectPrice','POST',{});

