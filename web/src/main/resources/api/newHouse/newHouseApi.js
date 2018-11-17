/**
 * 上传户型图
 */
$.namespace("com.toutiao.officedict.pic.saveHousingProjectLayoutImage");
com.toutiao.officedict.pic.saveHousingProjectLayoutImage=new com.toutiao.core.api.uploadApi('/newHouse/saveLayoutImage','POST',{});

/**
 * 户型图列表
 */
$.namespace("com.toutiao.officedict.pic.listHousingProjectLayoutImages");
com.toutiao.officedict.pic.listHousingProjectLayoutImages=new com.toutiao.core.api.listApi('/newHouse/listLayoutImages','GET',{});

/**
 * 删除户型图
 */
$.namespace("com.toutiao.officedict.pic.deleteHousingProjectLayoutImage");
com.toutiao.officedict.pic.deleteHousingProjectLayoutImage=new com.toutiao.core.api.singleApi('/newHouse/deleteLayoutImage','GET',{});

/**
 * 上传除户型图外的其他图片
 */
$.namespace("com.toutiao.officedict.pic.saveHousingProjectOtherImage");
com.toutiao.officedict.pic.saveHousingProjectOtherImage=new com.toutiao.core.api.uploadApi('/pic/saveHousingProjectOtherImage','POST',{});

/**
 * 除户型图外的其他图片列表
 */
$.namespace("com.toutiao.officedict.pic.listHousingProjectOtherImages");
com.toutiao.officedict.pic.listHousingProjectOtherImages=new com.toutiao.core.api.listApi('/pic/listHousingProjectOtherImages','GET',{});

/**
 * 除户型图外的其他图片列表
 */
$.namespace("com.toutiao.officedict.pic.esfPhotos");
com.toutiao.officedict.pic.esfPhotos=new com.toutiao.core.api.listApi('/pic/esfPhotos','GET',{});


/**
 * 设置合格或不合格
 */
$.namespace("com.toutiao.officedict.pic.setQualified");
com.toutiao.officedict.pic.setQualified=new com.toutiao.core.api.singleApi('/pic/setQualified','GET',{});

/**
 * 删除除户型图外的其他图
 */
$.namespace("com.toutiao.officedict.pic.deleteHousingProjectOtherImage");
com.toutiao.officedict.pic.deleteHousingProjectOtherImage=new com.toutiao.core.api.singleApi('/pic/deleteHousingProjectOtherImage','GET',{});
/**
* 将该图片作为标题图
*/
$.namespace("com.toutiao.officedict.pic.titleHousingProjectOtherImage");
com.toutiao.officedict.pic.titleHousingProjectOtherImage=new com.toutiao.core.api.singleApi('/pic/titleHousingProjectOtherImage','GET',{});
/**
 * 取消该标题图
 */
$.namespace("com.toutiao.officedict.pic.titleHousingProjectOtherImage");
com.toutiao.officedict.pic.cancelTitleHousingProjectOtherImage=new com.toutiao.core.api.singleApi('/pic/cancelTitleHousingProjectOtherImage','GET',{});
/**
 * 楼盘户型新增-新房
 */
$.namespace("com.toutiao.officedict.newHouse.saveHousingProjectLayout");
com.toutiao.officedict.newHouse.saveHousingProjectLayout=new com.toutiao.core.api.singleApi('/newHouse/saveHousingProjectLayout','POST',{});

/**
 * 楼盘户型列表-新房
 */
$.namespace("com.toutiao.officedict.newHouse.listHousingProjectLayout");
com.toutiao.officedict.newHouse.listHousingProjectLayout=new com.toutiao.core.api.listApi('/newHouse/listHousingProjectLayout','GET',{});

/**
 * 楼盘户型查询-新房
 */
$.namespace("com.toutiao.officedict.newHouse.getHousingProjectLayout");
com.toutiao.officedict.newHouse.getHousingProjectLayout=new com.toutiao.core.api.singleApi('/newHouse/getHousingProjectLayout','GET',{});

/**
 * 楼盘户型更新-新房
 */
$.namespace("com.toutiao.officedict.newHouse.updateHousingProjectLayout");
com.toutiao.officedict.newHouse.updateHousingProjectLayout=new com.toutiao.core.api.singleApi('/newHouse/updateHousingProjectLayout','POST',{});

/**
 * 楼盘户型删除-新房
 */
$.namespace("com.toutiao.officedict.newHouse.deleteHousingProjectLayout");
com.toutiao.officedict.newHouse.deleteHousingProjectLayout=new com.toutiao.core.api.singleApi('/newHouse/deleteHousingProjectLayout','POST',{});

/**
 * 某个楼盘的预售证信息列表
 */
$.namespace("com.toutiao.officedict.newHouse.listSalesLicenseInfo");
com.toutiao.officedict.newHouse.listSalesLicenseInfo=new com.toutiao.core.api.listApi('/newHouse/listSalesLicenseInfo','GET',{});

/**
 * 保存预售证信息
 */
$.namespace("com.toutiao.officedict.newHouse.saveSalesLicense");
com.toutiao.officedict.newHouse.saveSalesLicense=new com.toutiao.core.api.singleApi('/newHouse/saveSalesLicense','POST',{}, 'application/json;charset=UTF-8');

/**
 * 某个楼盘的楼盘动态信息列表
 */
$.namespace("com.toutiao.officedict.newHouse.listSalesDynamicInfo");
com.toutiao.officedict.newHouse.listSalesDynamicInfo=new com.toutiao.core.api.listApi('/newHouse/listSalesDynamicInfo','GET',{});

/**
 * 保存预售证信息
 */
$.namespace("com.toutiao.officedict.newHouse.saveDynamicInfo");
com.toutiao.officedict.newHouse.saveDynamicInfo=new com.toutiao.core.api.singleApi('/newHouse/saveDynamicInfo','POST',{}, 'application/json;charset=UTF-8');