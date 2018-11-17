/*
* 楼盘详情页
* */
$.namespace("com.toutiao.officedict.project.newHousingProjectDetail");
com.toutiao.officedict.project.newHousingProjectDetail=new com.toutiao.core.api.singleApi('/newHouse/getHousingProject','GET',{});

/*
* 录入新楼盘-新房
* */
$.namespace("com.toutiao.officedict.project.addNewHouseHousingProject");
com.toutiao.officedict.project.addNewHouseHousingProject=new com.toutiao.core.api.singleApi('/newHouse/saveHousingProject','POST',{});

/*
*  发布新楼盘-新房
* */
$.namespace("com.toutiao.officedict.project.releaseHousingProject");
com.toutiao.officedict.project.releaseHousingProject=new com.toutiao.core.api.singleApi('/newHouse/releaseHousingProject','POST',{});

/*
*  取消发布新楼盘-新房
* */
$.namespace("com.toutiao.officedict.project.cancelReleaseHousingProject");
com.toutiao.officedict.project.cancelReleaseHousingProject=new com.toutiao.core.api.singleApi('/newHouse/cancelReleaseHousingProject','POST',{});

/*
* 住宅类别（下拉框）
* */
$.namespace("com.toutiao.officedict.project.listResidentialCategory");
com.toutiao.officedict.project.listResidentialCategory=new com.toutiao.core.api.listApi('/basic/listResidentialCategory','GET',{});

/*
* 区域商圈（下拉框）
* */
$.namespace("com.toutiao.officedict.project.cascadeControl");
com.toutiao.officedict.project.cascadeControl=new com.toutiao.core.api.singleApi('/districtarea/cascadecontrol','POST',{});

/*
* 汉字转拼音
* */
$.namespace("com.toutiao.officedict.project.pinyinChange");
com.toutiao.officedict.project.pinyinChange=new com.toutiao.core.api.singleApi('/pinyin/changepinyin','GET',{});

/*
*  楼盘列表-新房
* */
$.namespace("com.toutiao.officedict.project.listNewHousingProject");
com.toutiao.officedict.project.listNewHousingProject=new com.toutiao.core.api.listApi('/newHouse/listHousingProject','GET',{});

/*
 * 获取某个楼盘信息-新房
 * */
$.namespace("com.toutiao.officedict.project.getHousingProject");
com.toutiao.officedict.project.getHousingProject=new com.toutiao.core.api.singleApi('/newHouse/getHousingProject','GET',{});

/*
 * 更新新楼楼盘-新房
 * */
$.namespace("com.toutiao.officedict.project.updateHousingProject");
com.toutiao.officedict.project.updateHousingProject=new com.toutiao.core.api.singleApi('/newHouse/updateHousingProject','POST',{});



/*
* 录入新楼盘-二手房
* */
$.namespace("com.toutiao.officedict.project.addEsfHousingProject");
com.toutiao.officedict.project.addEsfHousingProject=new com.toutiao.core.api.singleApi('/esf/saveHousingProject','POST',{});

/*
*  楼盘列表-二手房
* */
$.namespace("com.toutiao.officedict.project.listEsfHousingProject");
com.toutiao.officedict.project.listEsfHousingProject=new com.toutiao.core.api.listApi('/esf/listHousingProject','GET',{});

/*
*  发布新楼盘-二手房
* */
$.namespace("com.toutiao.officedict.project.releaseEsfHousingProject");
com.toutiao.officedict.project.releaseEsfHousingProject=new com.toutiao.core.api.singleApi('/esf/releaseHousingProject','POST',{});

/*
*  取消发布新楼盘-二手房
* */
$.namespace("com.toutiao.officedict.project.cancelEsfReleaseHousingProject");
com.toutiao.officedict.project.cancelEsfReleaseHousingProject=new com.toutiao.core.api.singleApi('/esf/cancelReleaseHousingProject','POST',{});

/*
* 住宅建筑类别
* */
$.namespace("com.toutiao.officedict.project.listResidentialBuildCategory");
com.toutiao.officedict.project.listResidentialBuildCategory=new com.toutiao.core.api.listApi('/basic/listResidentialBuildCategory','GET',{});

/*
 * 获取某个楼盘信息--二手房
 * */
$.namespace("com.toutiao.officedict.project.getEsfHousingProject");
com.toutiao.officedict.project.getEsfHousingProject=new com.toutiao.core.api.singleApi('/esf/getHousingProject','GET',{});

/*
 * 更新新楼楼盘--二手房
 * */
$.namespace("com.toutiao.officedict.project.updateEsfHousingProject");
com.toutiao.officedict.project.updateEsfHousingProject=new com.toutiao.core.api.singleApi('/esf/updateHousingProject','POST',{});

/*
 * 住宅建筑形式
 * */
$.namespace("com.toutiao.officedict.project.listResidentialBuildForm");
com.toutiao.officedict.project.listResidentialBuildForm=new com.toutiao.core.api.listApi('/basic/listResidentialBuildForm','GET',{});