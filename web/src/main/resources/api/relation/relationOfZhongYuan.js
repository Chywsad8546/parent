
$.namespace("com.toutiao.officedict.relation.zhongList");
com.toutiao.officedict.relation.zhongList=new com.toutiao.core.api.listApi('/zhongyuan/zhongyuanList','POST',{});

$.namespace("com.toutiao.officedict.relation.selectOneZhongYuan");
com.toutiao.officedict.relation.selectOneZhongYuan=new com.toutiao.core.api.singleApi('/zhongyuan/zhongYuanOne','POST',{});

$.namespace("com.toutiao.officedict.relation.updateZhongYuan");
com.toutiao.officedict.relation.updateZhongYuan=new com.toutiao.core.api.singleApi('/zhongyuan/updateZhongYuan','POST',{});

$.namespace("com.toutiao.officedict.relation.addZhongYuan");
com.toutiao.officedict.relation.addZhongYuan=new com.toutiao.core.api.singleApi('/zhongyuan/addZhongYuan','POST',{});

$.namespace("com.toutiao.officedict.relation.deleteZhongYuan");
com.toutiao.officedict.relation.deleteZhongYuan=new com.toutiao.core.api.singleApi('/zhongyuan/deleteZhongYuan','POST',{});