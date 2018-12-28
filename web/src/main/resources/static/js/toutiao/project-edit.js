function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}
var _hnewCode = getQueryString('newcode');
$(function(){
    var city_id;
    var _newCode = getQueryString('newcode');
    $('#sales-license').on('click', function () {
        window.location = "/mvc/index.html#/mvc/newHouse/sales-license-info.html" + "?newcode=" + _newCode;
    });

    $('#dynamic-info').on('click', function () {
        window.location = "/mvc/index.html#/mvc/newHouse/dynamic-info.html" + "?newcode=" + _newCode+"&cityId="+city_id;
    });
    $('#activity-info').on('click', function () {
        window.location = "/mvc/index.html#/mvc/newHouse/activity-info.html" + "?newcode=" + _newCode;
    });

    var _housingType = $('input[name="housingType"]').val();
    if (_housingType == 0){ //新房
        /*
         * 获取某个新房信息
         * */
        com.toutiao.officedict.project.getHousingProject.do({"newcode": _newCode},function(res){
            getHousingProject(_newCode,res);
            city_id = res.cityId;
        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });
    } else {
        /*
         * 获取某个二手房信息
         * */
        com.toutiao.officedict.project.getEsfHousingProject.do({"newcode": _newCode},function(res){

            getHousingProject(_newCode,res);

        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });
    }
});

function getHousingProject(_newCode,res){
    // if (res['finishdate'] != null) {
    //     res['finishdate'] = res['finishdate'].substr(0,10);
    // }
    // if (res['saledate'] != null) {
    //     res['saledate'] = res['saledate'].substr(0,10);
    // }

    var nicknameList = res.nickname.split(",");

    res.nickname=nicknameList;
    console.log(res);
    var getHousingProjectHtml = template('get-housing-project-template',res);
    var change = new com.toutiao.officedict.ui.cascadeControl('districtId','areaId',1,res.districtId,res.areaId);
    change.init();



    $('#get-housing-project-content').html(getHousingProjectHtml);

    var editor = new wangEditor('projdesc');

    // 普通的自定义菜单
    editor.config.menus = [
        'source',
        '|',     // '|' 是菜单组的分割线
        'bold',
        'underline',
        'italic',
        'strikethrough',
        'eraser',
        'forecolor',
        'bgcolor',
        '|',
        'orderlist',
        'unorderlist',
        'alignleft',
        'aligncenter',
        'alignright'
    ];
    editor.create();

    $('input[name="newcode"]').val(_newCode);

    if ($(".datepicker-item").length > 0){
        jQuery('.datepicker-item').datepicker({
            language:'zh-CN',
            autoclose: true,
            todayHighlight: true,
            format:"yyyy-mm-dd",
            pickerPosition: 'bottom-left'
        });
    }
    /*
     * 住宅类别
     * */
    com.toutiao.officedict.project.listResidentialCategory.do({},function (res) {

        renderCheckBox($('#residential-category'), res);

    },function (jqXHR, textStatus, errorThrown) {
        console.log('error',jqXHR, textStatus, errorThrown)
    });
    /*
     * 住宅建筑类别
     * */
    com.toutiao.officedict.project.listResidentialBuildCategory.do({},function (res) {

        renderCheckBox($('#build-category'), res);

    },function (jqXHR, textStatus, errorThrown) {
        console.log('error',jqXHR, textStatus, errorThrown)
    });
    /*
     * 住宅建筑形式
     * */
    com.toutiao.officedict.project.listResidentialBuildForm.do({},function (res) {

        renderCheckBox($('#build-form'), res);

    },function (jqXHR, textStatus, errorThrown) {
        console.log('error',jqXHR, textStatus, errorThrown)
    });
};


/*
 * 保存提交
 * */
$('#sava-success-btn').click(function(){
    $('#add-office-form').validate({
        rules: {
            projname: 'required',
            address: 'required',
            projType: 'required',
        },
        messages: {
            projname : '请输入楼盘名称',
            address: '请输入地址',
            projType: '请选择住宅类别',
        },
        submitHandler: function(){   //表单提交句柄,为一回调函数，带一个参数：form

            var result = $('#add-office-form').serializeJSON();
            var _housingType = $('input[name="housingType"]').val();

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
            result.priceUnit="元/㎡";
            result.propfeetype="元/㎡*月";
            if (_housingType == 0) {//新房
                result.totalPriceUnit="万";
                com.toutiao.officedict.project.updateHousingProject.do(result,function (res){
                    if (res && res>0){
                        com.toutiao.officedict.price.saveProjectPrice.do({newcode:_hnewCode,priceAverage:result.price,priceDate:getNowFormatDate()}, function  (res, success, error) {
                            swal({
                                title: "保存成功!",
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: false
                            }, function () {
                                swal.close();
                                //window.location.reload();
                                console.log(result)
                                /* window.location = '/index#mvc/newHouse/project-detail.html' + '?newcode=' + result.newcode;*/
                                window.location = '/mvc/index.html#/mvc/newHouse/project-edit.html' + '?newcode=' + result.newcode;
                            });
                        });
                    }
                },function (jqXHR, textStatus, errorThrown) {
                    console.log('error',jqXHR, textStatus, errorThrown)
                });

            }else{
                com.toutiao.officedict.project.updateEsfHousingProject.do(result,function (res){
                    if (res && res>0){
                        com.toutiao.officedict.price.saveProjectPrice.do({newcode:_hnewCode,priceAverage:result.esfPrice,priceDate:getNowFormatDate()}, function  (res, success, error) {
                            swal({
                                title: "保存成功!",
                                type: "success",
                                confirmButtonText: "确定",
                                closeOnConfirm: false
                            }, function () {
                                swal.close();
                                //window.location.reload();
                                console.log(result);
                                /*   window.location = '/index#mvc/esf/project-detail.html' + '?newcode=' + result.newcode;*/
                                window.location = '/mvc/index.html#/mvc/esf/project-edit.html' + '?newcode=' + result.newcode;
                            });
                        });
                    }
                },function (jqXHR, textStatus, errorThrown) {
                    console.log('error',jqXHR, textStatus, errorThrown)
                });
            }

        }
    });

});