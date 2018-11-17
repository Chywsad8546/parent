
$.namespace("com.toutiao.officedict.infrastruecture.house_building_category");

com.toutiao.officedict.infrastruecture.house_building_category.houselist=new com.toutiao.core.api.listApi('/basic/listResidentialBuildCategory','POST',{});

com.toutiao.officedict.infrastruecture.house_building_category.delhousebuild=new com.toutiao.core.api.singleApi('/basic/deleteResidentialBuildCategory','GET',{});

com.toutiao.officedict.infrastruecture.house_building_category.getonehousec=new com.toutiao.core.api.singleApi('/basic/getResidentialBuildCategory','POST',{});

com.toutiao.officedict.infrastruecture.house_building_category.uphousec=new com.toutiao.core.api.singleApi('/basic/updateResidentialBuildCategory','POST',{});

com.toutiao.officedict.infrastruecture.house_building_category.savehousebuild=new com.toutiao.core.api.singleApi('/basic/saveResidentialBuildCategory','POST',{});

