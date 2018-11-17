$(function(){
    $('#adddistrict').validate({

        rules: {
            districtName: 'required'
        },
        messages: {
            districtName: '请输入区域名称'
        }, submitHandler: function(){
            var result = $('#adddistrict').serializeJSON();
            var cityId=12;
            var districtName=eval('result.'+"districtName");
            com.toutiao.officedict.infrastruecture.district.adddistrict.do({"districtName":districtName,"cityId":cityId}, function (res,success, error) {
                if (res && res>0){
                    swal({
                        title: "保存成功!",
                        type: "success",
                        confirmButtonText: "确定",
                        closeOnConfirm: true
                    }, function () {
                        $('#addDis').modal('hide');
                        loadDistrictList(1);
                    });
                }
            },function (jqXHR, textStatus, errorThrown) {
                console.log('error',jqXHR, textStatus, errorThrown)
            });


        }


    });
    loadDistrictList();

    $('#districttable').ttTable({
        paging:true,
        columns:[
            {
                field:"districtId",
                "title":"区域ID"
            },
            {
                field:"districtName",
                "title":"区域名称"
            },
            {
                field:"cityName",
                "title":"所属城市"
            },
            {
                render:function (d) {

                    return "<div class='button-box'>"
                        + "<button id='district-edit' class='btn btn-info district-edit' data-id='" + d['districtId']+ "'>修改</button>"
                        + "<button id='district-delete' class='btn btn-info district-delete' data-id='" + d['districtId']+ "'>删除</button>"
                        + "</div>";
                },
                "title":"管理操作"
            }

        ],
        onPage:function (pageIndex, pageSize) {
            loadDistrictList(pageIndex);
        }}
    );


    var formparam = {};
    $('#search-list-district').click(function(){
        formparam = $('#pro_district_form').serializeJSON();
        loadDistrictList(1);
    });


    function loadDistrictList(pageNum) {


        var param =  $.extend({"pageNum":pageNum},formparam);
        com.toutiao.officedict.infrastruecture.district.searchdistrict.do(param,function (res, pageNum, pageSize, total) {

            $('#districttable').ttTable().showloading();

            $('#districttable').ttTable().load(res, total, pageNum);

            renderButton(pageNum);

        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });
    };






    $('#subsave').click(function () {

        // console.log(JSON.stringify(result));
        $('#adddistrict').submit();
    });
    function renderButton(pageNum) {

        $('.district-edit').each(function () {

            var _id = $(this).data('id');
            $(this).on('click', function () {

                com.toutiao.officedict.infrastruecture.district.searchdistrict.do({"districtId":_id}, function(res){
                    var upHtml = template('edit-districte-form-template',res[0]);
                    $('#upform').html(upHtml);
                    $('#upDis').modal();
                },function (jqXHR, textStatus, errorThrown) {
                    console.log('error',jqXHR, textStatus, errorThrown)
                });

            });
        });

        $('.district-delete').each(function () {
            var _id = $(this).data('id');
            $(this).on('click', function () {
                swal({
                    title: "您确定要删除区域吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                },function () {
                    com.toutiao.officedict.infrastruecture.district.deletedistrict.do({"id":_id}, function(res){
                        if (res && res>0) {
                            swal({
                                title: "删除成功!",
                                type: "success",
                                confirmButonText: "确定",
                                closeOnConfirm: false
                            }, function () {
                                swal.close();
                                loadDistrictList(pageNum);
                            });
                        }
                    },function (jqXHR, textStatus, errorThrown) {
                        console.log('error',jqXHR, textStatus, errorThrown)
                    });
                })
            });
        });


    }
    $('#subup').on('click', function () {
        var result = $('#editdistrict').serializeJSON();
        result = $.extend({"cityId":12},result);
        com.toutiao.officedict.infrastruecture.district.updatedistrict.do(result,function (res) {
            if (res && res>0) {
                swal({
                    title: "更新成功!",
                    type: "success",
                    confirmButtonText: "确定",
                    closeOnConfirm: false
                }, function () {
                    $('#upDis').modal('hide');
                    swal.close();
                    loadDistrictList($('#districttable').ttTable().pageNum);
                });

            }
        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });

    });
});







