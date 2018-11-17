//获取单个楼房信息
$.namespace("com.toutiao.officedict.esf.singesfl");
com.toutiao.officedict.esf.singesfl=new com.toutiao.core.api.singleApi('/esf/getHousingProject','GET',{});



//更改坐标
$.namespace("com.toutiao.officedict.esf.saveesfpoint");
com.toutiao.officedict.esf.saveesfpoint=new com.toutiao.core.api.singleApi('/esf/updateHousingProject','POST',{});