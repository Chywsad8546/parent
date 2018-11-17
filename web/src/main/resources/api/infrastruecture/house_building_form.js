
$.namespace("com.toutiao.officedict.infrastruecture.house_building_form");

com.toutiao.officedict.infrastruecture.house_building_form.formlist=new com.toutiao.core.api.listApi('/basic/listResidentialBuildForm','POST',{});

com.toutiao.officedict.infrastruecture.house_building_form.delform=new com.toutiao.core.api.singleApi('/basic/deleteResidentialBuildForm','POST',{});

com.toutiao.officedict.infrastruecture.house_building_form.uphouseform=new com.toutiao.core.api.singleApi('/basic/updateResidentialBuildForm','POST',{});

com.toutiao.officedict.infrastruecture.house_building_form.getonehouseform=new com.toutiao.core.api.singleApi('/basic/getResidentialBuildForm','GET',{});

com.toutiao.officedict.infrastruecture.house_building_form.addform=new com.toutiao.core.api.singleApi('/basic/saveResidentialBuildForm','POST',{});

