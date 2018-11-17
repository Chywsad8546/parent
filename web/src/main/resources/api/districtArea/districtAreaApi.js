/*
* 区域商圈绑定详情
* */
$.namespace("com.toutiao.officedict.project.districtAreaConfig");
com.toutiao.officedict.project.districtAreaConfig=new com.toutiao.core.api.singleApi('/districtarea/config-list','GET',{});

/*
* 未绑定商圈
* */
$.namespace("com.toutiao.officedict.project.districtAreaUnbound");
com.toutiao.officedict.project.districtAreaUnbound=new com.toutiao.core.api.listApi('/districtarea/unbound','GET',{});

/*
* 绑定区域商圈
* */
$.namespace("com.toutiao.officedict.project.districtAreaBinding");
com.toutiao.officedict.project.districtAreaBinding=new com.toutiao.core.api.singleApi('/districtarea/binding','POST',{});

/*
* 解绑区域商圈
* */
$.namespace("com.toutiao.officedict.project.districtAreaUnbundling");
com.toutiao.officedict.project.districtAreaUnbundling=new com.toutiao.core.api.singleApi('/districtarea/unbundling','POST',{});

$.namespace("com.toutiao.officedict.project.getDistrictId");
com.toutiao.officedict.project.getDistrictId=new com.toutiao.core.api.singleApi('/districtarea/getDistrictId','GET',{});