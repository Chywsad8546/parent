

$.namespace("com.toutiao.officedict.relation.maiList");
com.toutiao.officedict.relation.maiList=new com.toutiao.core.api.listApi('/maitian/maitianList','POST',{});/**
 * Created by 18710 on 2018/4/11.
 */

$.namespace("com.toutiao.officedict.relation.selectOneMaiTian");
com.toutiao.officedict.relation.selectOneMaiTian=new com.toutiao.core.api.singleApi('/maitian/maitianOne','POST',{});

$.namespace("com.toutiao.officedict.relation.updateMaiTian");
com.toutiao.officedict.relation.updateMaiTian=new com.toutiao.core.api.singleApi('/maitian/updateMaiTian','POST',{});

$.namespace("com.toutiao.officedict.relation.addMaiTian");
com.toutiao.officedict.relation.addMaiTian=new com.toutiao.core.api.singleApi('/maitian/addMaiTian','POST',{});

$.namespace("com.toutiao.officedict.relation.gettouname");
com.toutiao.officedict.relation.gettouname=new com.toutiao.core.api.singleApi('/maitian/touname','POST',{});

$.namespace("com.toutiao.officedict.relation.deleteMaiTian");
com.toutiao.officedict.relation.deleteMaiTian=new com.toutiao.core.api.singleApi('/maitian/deleteMaiTian','POST',{});

$.namespace("com.toutiao.officedict.relation.importMaiTian");
com.toutiao.officedict.relation.importMaiTian=new com.toutiao.core.api.singleApi('/maitian/importMaiTian','POST',{});