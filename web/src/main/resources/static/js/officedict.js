if(!window.$toutiao) {
    window.$toutiao = {}
}

window.$toutiao.tips={
    times:3500,
    error:function () {
        var title="错误";
        var message=arguments[0];
        if(arguments.length>1){
            title=arguments[0];
            message=arguments[1];
        }
        $.toast({
            heading: title,
            text:message,
            position: 'top-right',
            loaderBg: '#ff6849',
            icon: 'error',
            hideAfter: window.$toutiao.tips.times

        });
    }
};
window.$toutiao.vvapi={
    get:function (url,data,timeout,callback) {
        $.ajax({
            cache:false,
            url:url,
            data:data,
            dataType:'json',
            type:'GET',
            timeout:timeout
        }).done(function(data) {
            try{
                if(data["code"]=="success"){
                    callback(data);
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
window.$toutiao.form={
    fromQuery:function(formid,callback){
        var State = History.getState();
        console.log(State);
        var json = $toutiao.url.queryToJson(State.hashedUrl);
        console.log(json);
        window.$toutiao.form.fromJson(formid,json,callback);
    },
    fromJson:function (formid,json,callback) {
        try {
            for (var name in json) {
                var value = json[name];
                var eles = $(formid).find('[name="' + name + '"]');
                var tagName = eles.prop('tagName');
                if (tagName && eles.size() > 0) {
                    tagName = tagName.toLowerCase();
                    if (tagName == 'input' &&
                        (
                            (eles.attr('type') && eles.attr('type').toLowerCase() == 'radio')
                            ||
                            (eles.attr('type') && eles.attr('type').toLowerCase() == 'checkbox')
                        )
                    ) {
                        if (!$.isArray(value)) {
                            value = [value];
                        }
                    }
                    else if (tagName == 'select' && eles.attr('multiple')) {
                        if (!$.isArray(value)) {
                            value = [value];
                        }
                    }
                    eles.val(value);
                }
            }
        }
        catch (e){
            console.error(e);
            callback && callback(e);
        }
    }
};
window.$toutiao.url={
    queryToJson:function (url) {
        var url = new URI(url);
        return url.query(true);
    }
}
$.extend({"toutiao":window.$toutiao});

