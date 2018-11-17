$.namespace("com.toutiao.officedict.relation.woaiList");
com.toutiao.officedict.relation.woaiList=new com.toutiao.core.api.listApi('/woai/woAiList','POST',{});

$.namespace("com.toutiao.officedict.relation.selectOneWoAi");
com.toutiao.officedict.relation.selectOneWoAi=new com.toutiao.core.api.singleApi('/woai/woAiOne','POST',{});

$.namespace("com.toutiao.officedict.relation.updateWoai");
com.toutiao.officedict.relation.updateWoai=new com.toutiao.core.api.singleApi('/woai/updateWoAi','POST',{});

$.namespace("com.toutiao.officedict.relation.addWoai");
com.toutiao.officedict.relation.addWoai=new com.toutiao.core.api.singleApi('/woai/addWoai','POST',{});

$.namespace("com.toutiao.officedict.relation.deleteWoai");
com.toutiao.officedict.relation.deleteWoai=new com.toutiao.core.api.singleApi('/woai/deleteWoai','POST',{});

$.namespace("com.toutiao.officedict.relation.importWoai");
com.toutiao.officedict.relation.importWoai=new com.toutiao.core.api.singleApi('/woai/importWoai','POST',{});