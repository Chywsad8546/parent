$.namespace("com.toutiao.officedict.ui.cascadeControl");
com.toutiao.officedict.ui.cascadeControl = function(district_id,area_id,house_type,districtId,areaId) {
    this.district_id=district_id;
    this.area_id=area_id;
    this.districtinfo_dict={};
    var that=this;
    this.init=function(){
        $('#'+that.district_id).attr("disabled","disabled");
        /**
         * 区域商圈级联
         */
        com.toutiao.officedict.project.cascadeControl.do({houseType:house_type},function(res){
            that.changeControl(res);
            $('#'+that.district_id).removeAttr("disabled");
        },function (jqXHR, textStatus, errorThrown) {
        });
    };
     this.changeControl = function (res) {
        var areas=res["area"];
         var districts=res["district"];
        $('#'+that.district_id).on('change',function () {
            var district_value =$('#'+that.district_id).val();
            $('#'+that.area_id+' option').remove();
            $('#'+that.area_id).append(new Option("--请选择商圈--", ''));
            $.each(areas,function (index,ele) {

                if(ele["districtID"]==district_value){
                    $('#'+that.area_id).append(new Option(ele.areaName, ele.areaId))
                }
            })

        });

        /**
         * 区域
         * @type {Array}
         */

        $.each(districts, function (index, item) {

                that.districtinfo_dict[''+item.districtId]=item;

                if(districtId == item.districtId){
                    $('#'+that.district_id).append(new Option(item.districtName, item.districtId,true, true));
                  //  console.log(areas);
                        $.each(areas,function (index,ele) {
                           // console.log(areaId,ele.areaId);
                            if(ele["districtID"]==districtId){
                                if(areaId == ele.areaId){
                                    $('#'+that.area_id).append(new Option(ele.areaName, ele.areaId,true, true))
                                }else {
                                    $('#'+that.area_id).append(new Option(ele.areaName, ele.areaId))
                                }
                            }
                        })
                }else {
                    $('#'+that.district_id).append(new Option(item.districtName, item.districtId))
                }

        });


    }

};


