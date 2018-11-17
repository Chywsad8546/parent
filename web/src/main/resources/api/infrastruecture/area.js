$.namespace("com.toutiao.officedict.infrastruecture.area");

com.toutiao.officedict.infrastruecture.area.arealist=new com.toutiao.core.api.listApi('/area/area-list','GET',{});
com.toutiao.officedict.infrastruecture.area.arealistByDistrictId=new com.toutiao.core.api.listApi('/area/area-listByDistrictId','GET',{});


com.toutiao.officedict.infrastruecture.area.addarea=new com.toutiao.core.api.singleApi('/area/add-area','POST',{});

com.toutiao.officedict.infrastruecture.area.updatearea=new com.toutiao.core.api.singleApi('/area/update-area','POST',{});

com.toutiao.officedict.infrastruecture.area.delarea=new com.toutiao.core.api.singleApi('/area/del-area','POST',{});


/*
 * 汉字转拼音
 * */
com.toutiao.officedict.infrastruecture.areaPinTinChange=new com.toutiao.core.api.singleApi('/pinyin/changepinyin','GET',{});
