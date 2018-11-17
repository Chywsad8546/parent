$(function(){


    /*
     * 汉字转拼音
     * */
    $('#projname').bind('input propertychange', function () {
        com.toutiao.officedict.project.pinyinChange.do({name:$('#projname').val()},function(res){
            $('#pinyinName').val(res.pingYin);
        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });
    });
    /*
     * 住宅类别
     * */
    com.toutiao.officedict.project.listResidentialCategory.do({}, function(res){
        var listResidentialCategoryHtml = template('list-residential-category-template',{"res":res});
        $('#list-residential-category-content').html(listResidentialCategoryHtml);
      console.log(res)
    },function (jqXHR, textStatus, errorThrown) {
        console.log('error',jqXHR, textStatus, errorThrown)
    });
    /*
    * 住宅建筑类别
    * */
    com.toutiao.officedict.project.listResidentialBuildCategory.do({},function (res) {
        var listResidentialBuildCategoryHtml = template('list-residential-build-category-template',{"res":res});
        $('#list-residential-build-category-content').html(listResidentialBuildCategoryHtml);
    },function (jqXHR, textStatus, errorThrown) {
        console.log('error',jqXHR, textStatus, errorThrown)
    });
    /*
     * 住宅建筑形式
     * */
    com.toutiao.officedict.project.listResidentialBuildForm.do({},function (res) {
        var listResidentialBuildFormHtml = template('list-residential-build-form-template',{"res":res});
        $('#list-residential-build-form-content').html(listResidentialBuildFormHtml);
    },function (jqXHR, textStatus, errorThrown) {
        console.log('error',jqXHR, textStatus, errorThrown)
    });
    /*
     *  区域商圈级联
     * */
    $(".radioItem").change(function() {
        var houseType = $('input[name="housingType"]:checked').val();
        var change = new com.toutiao.officedict.ui.cascadeControl('districtId','areaId',houseType);
        change.init();
    });
});
/*
 * 保存提交
 * */
$('#sava-success-btn').click(function(){
    $('#add-office-form').validate({
        rules: {
            houseType: 'required',
            projname: 'required',
            pinyinName: 'required',
            address: 'required',
            projType: 'required',
            buildcategory: 'required',
            buildingform: 'required',
            districtId: 'required',
            areaId: 'required'
        },
        messages: {
            houseType: '请选择楼盘类型',
            projname : '请输入楼盘名称',
            pinyinName: '请输入楼盘拼音',
            address: '请输入地址',
            projType: '请选择住宅类别',
            buildcategory: '请选择建筑类别',
            buildingform: '请选择建筑形式',
            districtId: '请选择区域',
            areaId: '请选择商圈'
        },
        submitHandler: function(){   //表单提交句柄,为一回调函数，带一个参数：form
            var result = $('#add-office-form').serializeJSON();

            console.log(result);

            var _housingType = $('input[name="housingType"]:checked').val();

            var $test = $("input[name=nickname]");
            for(var i=0;i<$test.length;i++){
                console.log($test.eq(i).val().trim())
                if(i==0){
                    if($test.eq(i).val().trim()!=""){
                        result.nickname=$test.eq(i).val().trim();
                    }else {
                        result.nickname="";
                    }
                }else {
                    if($test.eq(i).val().trim()!=""){
                        result.nickname+=","+$test.eq(i).val().trim();
                    }
                }
            }

            if ( result.nickname.substr(0,1)==','){
                result.nickname= result.nickname.substr(1);
            }

            if (_housingType == 0) {//新房楼盘录入
                com.toutiao.officedict.project.addNewHouseHousingProject.do(result,function (res) {
                    if (res && res>0) {
                        swal({
                            title: "保存成功!",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: false
                        }, function () {
                            window.location.reload();
                        });

                    }
                },function (jqXHR, textStatus, errorThrown) {
                    console.log('error',jqXHR, textStatus, errorThrown)
                });
            } else {//二手房楼盘录入
                com.toutiao.officedict.project.addEsfHousingProject.do(result,function (res) {
                    console.log(result)
                   if (res && res>0) {
                        swal({
                            title: "保存成功!",
                            type: "success",
                            confirmButtonText: "确定",
                            closeOnConfirm: false
                        }, function () {
                            window.location.reload();
                        });

                    }
                },function (jqXHR, textStatus, errorThrown) {
                    console.log('error',jqXHR, textStatus, errorThrown)
                });
            }

        }
    });

});
