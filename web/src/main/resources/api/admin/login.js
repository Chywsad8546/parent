/**
 * 用户登录
 */

$.namespace("com.toutiao.officedict.project.login");
com.toutiao.officedict.project.login=new com.toutiao.core.api.singleApi('/userlogin','GET',{});

$.namespace("com.toutiao.officedict.project.logout");
com.toutiao.officedict.project.logout=new com.toutiao.core.api.singleApi('/logout','GET',{});

$.namespace("com.toutiao.officedict.project.changeCity");
com.toutiao.officedict.project.changeCity=new com.toutiao.core.api.singleApi('/changeCity','GET',{});
