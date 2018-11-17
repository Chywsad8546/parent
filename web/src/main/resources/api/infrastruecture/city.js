
$.namespace("com.toutiao.officedict.infrastruecture.city");

com.toutiao.officedict.infrastruecture.city.searchcity=new com.toutiao.core.api.listApi('/city/city-list','POST',{});

com.toutiao.officedict.infrastruecture.city.deletecity=new com.toutiao.core.api.singleApi('/city/del-city','POST',{});

com.toutiao.officedict.infrastruecture.city.updatecity=new com.toutiao.core.api.singleApi('/city/update-city','POST',{});

com.toutiao.officedict.infrastruecture.city.addcity=new com.toutiao.core.api.singleApi('/city/add-city','POST',{});

/*
 * 汉字转拼音
 * */
$.namespace("com.toutiao.officedict.project.CityPinyinChange");
com.toutiao.officedict.project.CityPinyinChange=new com.toutiao.core.api.singleApi('/pinyin/changepinyin','GET',{});
