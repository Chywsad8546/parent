
$.namespace("com.toutiao.officedict.infrastruecture.district");

com.toutiao.officedict.infrastruecture.district.searchdistrict=new com.toutiao.core.api.listApi('/district/district-list','GET',{});

com.toutiao.officedict.infrastruecture.district.deletedistrict=new com.toutiao.core.api.singleApi('/district/del-district','GET',{});

com.toutiao.officedict.infrastruecture.district.updatedistrict=new com.toutiao.core.api.singleApi('/district/update-district','POST',{});

com.toutiao.officedict.infrastruecture.district.adddistrict=new com.toutiao.core.api.singleApi('/district/add-district','POST',{});
