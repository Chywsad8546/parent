$.fn.extend({
    TtAjaxTable:function (dist) {
        $(this).unbind("submit");
        /**
         * 初始化两个隐藏域
         */
        if($(this).find('input[hidden][name="pageNum"]').size()<1){
            $(this).append("<input name='pageNum' type='hidden' value='1' />");
        }
        if($(this).find('input[hidden][name="pageSize"]').size()<1){
            var pagesize=10;
            if($(this).attr("pageSize")){
                pagesize=$(this).attr("pageSize");
            }
            $(this).append("<input name='pageSize' type='hidden' value='"+pagesize+"' />");
        }
        /**
         * 拦截form提交事件
         */
        $(this).submit(function (e) {
            $(this).find('input[name="pageNum"]').val(1);
            tijiao($(this));
            e.preventDefault();
            return false;
        });

        function tijiao(ele) {
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
                        $(dist).children('tbody').remove();
                        var tbody = $("<tbody></tbody>");

                        $.each(data["data"]["data"],function () {
                            var jsonobj=this;
                            var tr = $("<tr></tr>");

                            $(dist).children("thead").find("th:visible").each(function () {

                                if($(this).attr("field")){
                                    tr.append("<td>"+jsonobj[$(this).attr("field")]+"</td>");
                                }
                                else if($(this).find('[type="x-tmpl-mustache"]').size()>0) {
                                    var template = $(this).find('[type="x-tmpl-mustache"]').eq(0).html();
                                    var rendered = Mustache.render(template, jsonobj);
                                    tr.append("<td>"+rendered+"</td>");
                                }
                                else {
                                    tr.append("<td><input name='proj-id' value='" + jsonobj.nid + "' type='checkbox'/></td>");
                                }

                            });
                            tbody.append(tr);
                        });
                        $(dist).append(tbody);
                        $(dist).iCheck({
                            checkboxClass: 'icheckbox_minimal',
                            radioClass : 'iradio_minimal'
                        });

                        var pageindex = data["data"]["pageNum"];
                        var pagesize = data["data"]["pageSize"];
                        var total = data["data"]["total"];
                        if(pagesize>0){
                            total = total/pagesize + ((total%pagesize)==0?0:1);
                        }

                        total = (total==0)?1:total;
                        if(pageindex<1 || pageindex>total){
                            pageindex=1;
                        }

                        var options = {
                            currentPage: pageindex,
                            totalPages: total,
                            bootstrapMajorVersion:3,
                            itemTexts: function (type, page, current) {
                                switch (type) {
                                    case "first":
                                        return "首页";
                                    case "prev":
                                        return "上一页";
                                    case "next":
                                        return "下一页";
                                    case "last":
                                        return "尾页";
                                    case "page":
                                        return  page;
                                }
                            },
                            itemContainerClass: function (type, page, current) {
                                return ((page === current) && type=='page') ? "active" : "";
                            },
                            shouldShowPage:function(type, page, current){
                                return true;
                            },
                            pageUrl: function(type, page, current){

                                return "javascript:void(0);";

                            },
                            onPageClicked: function(e,originalEvent,type,page) {
                                e.stopImmediatePropagation();
                                ele.find('input[name="pageNum"]').val(page);
                                tijiao(ele);
                            }
                        }

                        $(dist).find("tfoot td ul.pagination").bootstrapPaginator(options);
                    }
                    else {
                        $.toutiao.tips.error(data["msg"]);
                    }
                }
                catch(e) {
                    console.log(e);
                    $.toutiao.tips.error("数据解析异常");
                }

            })
                .fail(function(xhr,textStatus,errorThrown) {
                    if (textStatus == "timeout") {
                        $.toutiao.tips.error("加载超时，请重试");
                    }
                    else {
                        $.toutiao.tips.error(textStatus,errorThrown);
                    }
                });
        }
    }
});