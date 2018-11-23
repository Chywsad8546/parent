$(function(){

    loadProjectList();

    $('#listpro').ttTable({
        paging:true,
        columns:[
            {
                field:"newcode",
                "title":"楼盘ID"
            },
            {
                field:"projname",
                "title":"楼盘名称"
            },
            {
                field:"districtName",
                "title":"区县"
            },
            {
                field:"areaName",
                "title":"商圈"
            },
            {
                field:"updateTime",
                "title":"修改时间"
            },
            {
                render:function (d) {
                    if( d['isApprove'] == 1){
                        return "<div class='button-box'>"
                            + "<button class='btn btn-info project-detail' data-id='" + d['newcode']+ "'>详情</button>"
                            + "<button class='btn btn-info project-layout' data-id='" + d['newcode']+ "'>户型管理</button>"
                            + "<button class='btn btn-info project-price' data-id='" + d['newcode']+ "'>价格</button>"
                            + "<button class='btn btn-info project-album'  data-id='" + d['newcode']+ "'>相册</button>"
                            + "<button class='btn btn-info project-cancelrelease' data-id='" + d['newcode']+ "'>取消发布</button>"
                        //    + "<button class='btn btn-info project-coordinate' data-id='" + d['newcode']+ "'>坐标</button>"
                            + "</div>";
                    }else{
                        return "<div class='button-box'>"
                            + "<button class='btn btn-info project-detail' data-id='" + d['newcode']+ "'>详情</button>"
                            + "<button class='btn btn-info project-layout' data-id='" + d['newcode']+ "'>户型管理</button>"
                            + "<button class='btn btn-info project-price' data-id='" + d['newcode']+ "'>价格</button>"
                            + "<button class='btn btn-info project-album' data-id='" + d['newcode']+ "'>相册</button>"
                            + "<button class='btn btn-info project-release' data-id='" + d['newcode']+ "'>发布</button>"
                        //    + "<button class='btn btn-info project-coordinate' data-id='" + d['newcode']+ "'>坐标</button>"
                            + "</div>";
                    }

                },
                "title":"管理操作"
            }

        ],
        onPage:function (pageIndex, pageSize) {
            loadProjectList(pageIndex);
        }}
    );

    /**
     * 根据条件查询楼盘
     */
    var formparam = $('#pro_list_form').serializeJSON();
    $('#search-list-pro').click(function(){
        formparam = $('#pro_list_form').serializeJSON();
        loadProjectList(1)
    });



    function loadProjectList(pageNum) {
        var param = $.extend({"pageNum":pageNum},formparam);
        com.toutiao.officedict.project.listEsfHousingProject.do(param,function (res, pageNum, pageSize, total) {
            console.log(res);

            $('#listpro').ttTable().showloading();

            $('#listpro').ttTable().load(res, total, pageNum);

            esfRenderButton(pageNum);

        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });
    };

    function esfRenderButton(pageNum) {

    /*    //跳转楼盘详情页
        $('.project-detail').each(function () {
            var _id = $(this).data('id');
            $(this).on('click', function () {
                window.location = '/index#mvc/esf/project-detail.html' + '?newcode=' + _id;
            });
        });*/


        //跳转楼盘修改页
        $('.project-detail').each(function () {
            var _id = $(this).data('id');
            $(this).on('click', function () {
                window.location = '/mvc/index.html#/mvc/esf/project-edit.html' + '?newcode=' + _id;
            });
        });


        //跳转户型管理页面
        $('.project-layout').each(function () {

            var _id = $(this).data('id');

            $(this).on('click', function () {
                window.location = '/mvc/index.html#/mvc/project-layout.html' + '?newcode=' + _id;
            });
        });


        //跳转价格管理页面
        $('.project-price').each(function () {

            var _id = $(this).data('id');

            $(this).on('click', function () {
                window.location = '/mvc/index.html#/mvc/price-management.html' + '?newcode=' + _id;
            });
        });

        //跳转相册管理页面
        $('.project-album').each(function () {

            var _id = $(this).data('id');

            $(this).on('click', function () {
                window.location = '/mvc/index.html#/mvc/project-album.html' + '?newcode=' + _id;
            });
        });

        //跳转地图坐标页
        $('.project-coordinate').each(function () {
            var _id = $(this).data('id');

            $(this).on('click', function () {
                window.location = '/mvc/index.html#project-coordinate.html' + '?newcode=' + _id;
            });
        });

        //发布
        $('.project-release').each(function () {
            var _id = $(this).data('id');
            $(this).on('click', function () {
                swal({
                    title: "您确定要发布二手房楼盘吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#dd5a55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false,
                    closeOnCancel: true
                },function () {
                    com.toutiao.officedict.project.releaseEsfHousingProject.do({"newcode":_id},function(res){

                        console.log(JSON.stringify(res));
                        if (res && res>0) {
                            swal({
                                title: "发布成功!",
                                type: "success",
                                confirmButonText: "确定",
                                closeOnConfirm: false
                            }, function () {
                                swal.close();
                                loadProjectList(pageNum);
                            });
                        }else{
                            swal({
                                title: "发布失败!",
                                type: "",
                                confirmButonText: "确定",
                                closeOnConfirm: false
                            }, function () {
                                swal.close();
                            });
                        }

                    },function (jqXHR, textStatus, errorThrown) {
                        console.log('error',jqXHR, textStatus, errorThrown)
                    });
                });
            });
        });

        //取消发布
        $('.project-cancelrelease').each(function () {
            var _id = $(this).data('id');
            $(this).on('click', function () {
                swal({
                    title: "您确定取消发布二手房楼盘吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false,
                    closeOnCancel: true
                },function () {
                    com.toutiao.officedict.project.cancelEsfReleaseHousingProject.do({"newcode":_id},function(res){

                        if (res && res>0) {
                            swal({
                                title: "取消发布成功!",
                                type: "success",
                                confirmButonText: "确定",
                                closeOnConfirm: false
                            }, function () {
                                swal.close();
                                loadProjectList(pageNum);
                            });
                        }else{
                            swal({
                                title: "取消发布失败!",
                                type: "",
                                confirmButonText: "确定",
                                closeOnConfirm: false
                            }, function () {
                                swal.close();

                            });
                        }

                    },function (jqXHR, textStatus, errorThrown) {
                        console.log('error',jqXHR, textStatus, errorThrown)
                    });
                });
            });
        });

    }


    /**
     * 区域商圈级联
     */
    var change = new com.toutiao.officedict.ui.cascadeControl('districtId','areaId',1);
    change.init();

});

// /**
//  * 根据条件查询楼盘
//  */
// $('#search-list-pro').click(function(){
//     var result = $('#pro_list_form').serializeJSON();
//     // console.log(JSON.stringify(result));
//     com.toutiao.officedict.project.listEsfHousingProject.do(result,function (res, pageNum, pageSize, total) {
//         $('#listpro').ttTable().showloading();
//         setTimeout(function () {
//             $('#listpro').ttTable().load(res, total, pageNum);
//         },2000);
//     },function (jqXHR, textStatus, errorThrown) {
//         console.log('error',jqXHR, textStatus, errorThrown)
//     });
// });