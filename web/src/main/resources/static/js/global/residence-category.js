$(function () {

    renderTable($('#residence-category-table'));

    $('#residence-category-form').submit(function (e) {

        var _input = $('input[name="categoryName"]');

        if (!_input.val()) {
            alert('请输入住宅类别！');
            _input.focus();
            return false;
        }

        submitForm($(this));
        e.preventDefault();
        return false;
    });

    function submitForm(ele) {
        var url = ele.attr("action");
        var data=ele.serialize();

        $.ajax({
            cache:false,
            url:url,
            data:data,
            dataType:'json',
            type:'POST',
            timeout:30000
        }).done(function(data) {
            try{
                if(data["code"]=="success"){
                    renderTable($('#residence-category-table'));
                }
                else {
                    $.toutiao.tips.error(data["msg"]);
                }
            }
            catch(e) {
                console.log(e);
                $.toutiao.tips.error("数据解析异常");
            }

        }).fail(function(xhr,textStatus,errorThrown) {
                if (textStatus == "timeout") {
                    $.toutiao.tips.error("加载超时，请重试");
                }
                else {
                    $.toutiao.tips.error(textStatus,errorThrown);
                }
        });
    };

    function renderTable(dist) {
        $.ajax({
            cache:false,
            url:"/listResidentialCategory",
            dataType:'json',
            type:'POST',
            timeout:30000
        }).done(function (data) {
            try{
                if(data["code"]=="success"){
                    $(dist).children('tbody').remove();
                    var tbody = $("<tbody></tbody>");

                    $.each(data["data"],function () {
                        var jsonobj=this;
                        var tr = $("<tr></tr>");

                        $(dist).children("thead").find("th:visible").each(function () {

                            if($(this).attr("field")){
                                tr.append("<td>"+jsonobj[$(this).attr("field")]+"</td>");
                            } else if ($(this).data('operation')) {
                                tr.append("<td>修改 <button data-id='" + jsonobj[$(this).data('operation')] + "' name='del'>删除</button></td>");
                            } else if ($(this).data('sort')) {
                                tr.append("<td>上移 下移</td>");
                            } else {
                                tr.append("<td></td>");
                            }

                        });
                        tbody.append(tr);
                    });
                    $(dist).append(tbody);

                    //新增元素绑定事件
                    bindButton();
                }
                else {
                    $.toutiao.tips.error(data["msg"]);
                }
            }
            catch(e) {
                console.log(e);
                $.toutiao.tips.error("数据解析异常");
            }
        });
    };

    function bindButton () {
        $('button[name="del"]').each(function () {
            $(this).click(function () {
                $.ajax({
                    url: "/residentialCategory/",
                    type: "DELETE",
                    contentType : 'application/json;charset=utf-8', //设置请求头信息
                    dataType:"json",
                    async: false,//明确是异步提交数据
                    data: JSON.stringify($(this).data('id')), //将Json对象序列化成Json字符串，JSON.stringify()原生态方法
                    success: function(data){
                        if (data["code"]=="success") {
                            alert('删除成功');//提示操作成功
                            renderTable($('#residence-category-table'));
                        }
                    },
                    error: function(data){
                        $.toutiao.tips.error(data["msg"]);
                    }
                });
            });
        });
    }

});