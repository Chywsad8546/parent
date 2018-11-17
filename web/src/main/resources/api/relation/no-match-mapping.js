/*
*  新房楼盘价格管理
* */
$.namespace("com.toutiao.officedict.mapping.listNotMatchMappings");
com.toutiao.officedict.mapping.listNotMatchMappings=new com.toutiao.core.api.listApi('/mapping/listNotMatchMappings','GET',{});

$.namespace("com.toutiao.officedict.mapping.selectNotMatch");
com.toutiao.officedict.mapping.selectNotMatch=new com.toutiao.core.api.singleApi('/mapping/selectNotMatch','POST',{});

$.namespace("com.toutiao.officedict.mapping.updateStutas");
com.toutiao.officedict.mapping.updateStutas=new com.toutiao.core.api.singleApi('/mapping/updateStutas','POST',{});

$.namespace("com.toutiao.officedict.mapping.deleteRele");
com.toutiao.officedict.mapping.deleteRele=new com.toutiao.core.api.singleApi('/mapping/deleteRele','POST',{});

$.namespace("com.toutiao.officedict.mapping.getCompanys");
com.toutiao.officedict.mapping.getCompanys=new com.toutiao.core.api.listApi('/mapping/getCompanys','POST',{});