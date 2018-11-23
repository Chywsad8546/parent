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
                field:"createTime",
                "title":"录入时间"
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
                            + "<button class='btn btn-info project-album' data-id='" + d['newcode']+ "'>相册</button>"
                            + "<button class='btn btn-info project-cancelrelease' data-id='" + d['newcode']+ "'>取消发布</button>"
                       //     + "<button class='btn btn-info project-coordinate' data-id='" + d['newcode']+ "'>坐标</button>"
                            + "</div>";
                    }
                    return "<div class='button-box'>"
                         + "<button class='btn btn-info project-detail' data-id='" + d['newcode']+ "'>详情</button>"
                         + "<button class='btn btn-info project-layout' data-id='" + d['newcode']+ "'>户型管理</button>"
                         + "<button class='btn btn-info project-price' data-id='" + d['newcode']+ "'>价格</button>"
                         + "<button class='btn btn-info project-album' data-id='" + d['newcode']+ "'>相册</button>"
                         + "<button class='btn btn-info project-release' data-id='" + d['newcode']+ "'>发布</button>"
                      //   + "<button class='btn btn-info project-coordinate' data-id='" + d['newcode']+ "'>坐标</button>"
                         + "</div>";
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
        loadProjectList(1);
    });


    function loadProjectList(pageNum) {
        var param = $.extend({"pageNum":pageNum},formparam)
        com.toutiao.officedict.project.listNewHousingProject.do(param,function (res, pageNum, pageSize, total) {

            $('#listpro').ttTable().showloading();

            $('#listpro').ttTable().load(res, total, pageNum);

            renderButton(pageNum);

        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });
    };

    /**
     * 按钮绑定事件
     * @param _id
     * @param pageNum
     */
    function renderButton(pageNum) {

     /*   //跳转楼盘详情页
        $('.project-detail').each(function () {
            var _id = $(this).data('id');
            $(this).on('click', function () {
                window.location = '/index#mvc/newHouse/project-detail.html' + '?newcode=' + _id;
            });
        });*/


        //跳转楼盘修改页
        $('.project-detail').each(function () {

            var _id = $(this).data('id');
            $(this).on('click', function () {
                window.location = '/mvc/index.html#/mvc/newHouse/project-edit.html' + '?newcode=' + _id;
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
                    title: "您确定要发布楼盘吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                },function () {
                    com.toutiao.officedict.project.releaseHousingProject.do({"newcode":_id},function(res){
                        if (res && res>0) {
                            swal({
                                title: "发布成功!",
                                type: "success",
                                confirmButonText: "确定",
                                cancleButonText:"取消",
                                closeOnConfirm: false,
                                closeOnCancel: false
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

        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });

        //发布
        $('.project-cancelrelease').each(function () {
            var _id = $(this).data('id');
            $(this).on('click', function () {
                swal({
                    title: "您确定取消发布楼盘吗？",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    closeOnConfirm: false
                },function () {
                    com.toutiao.officedict.project.cancelReleaseHousingProject.do({"newcode":_id},function(res){

                        console.log(JSON.stringify(res));
                        if (res && res>0) {
                            swal({
                                title: "取消发布成功!",
                                type: "success",
                                confirmButonText: "确定",
                                closeOnConfirm: false,
                                closeOnCancel: false
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
    var change = new com.toutiao.officedict.ui.cascadeControl('districtId','areaId',0);
    change.init();

});