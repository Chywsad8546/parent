
$.namespace("com.toutiao.officedict.infrastruecture.house_category");

com.toutiao.officedict.infrastruecture.house_category.houselist=new com.toutiao.core.api.listApi('/basic/listResidentialCategory','POST',{});

com.toutiao.officedict.infrastruecture.house_category.addhouse=new com.toutiao.core.api.singleApi('/basic/saveResidentialCategory','POST',{});

com.toutiao.officedict.infrastruecture.house_category.getonehouse=new com.toutiao.core.api.singleApi('/basic/getResidentialCategory','POST',{});

com.toutiao.officedict.infrastruecture.house_category.updatehouse=new com.toutiao.core.api.singleApi('/basic/updateResidentialCategory','POST',{});

com.toutiao.officedict.infrastruecture.house_category.delhouse=new com.toutiao.core.api.singleApi('/basic/deleteResidentialCategory','POST',{});