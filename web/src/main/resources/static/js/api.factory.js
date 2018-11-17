$.namespace = function() {
    var a=arguments, o=null, i, j, d;
    for (i=0; i<a.length; i=i+1) {
        d=a[i].split(".");
        o=window;
        for (j=0; j<d.length; j=j+1) {
            o[d[j]]=o[d[j]] || {};
            o=o[d[j]];
        }
    }
    return o;
};
$.namespace("com.toutiao.core.api");
$.namespace("com.toutiao.core.api.base_url");

com.toutiao.core.api={
    timeout:5000,
    base_url:""
};
com.toutiao.core.api.abort=function (xhr) {
    if ( xhr && xhr.readyState < 4) {
        xhr.onreadystatechange = $.noop
        xhr.abort()
    }
};
com.toutiao.core.api.singleApi= function (url,method,param, contentType) {
    this.url = url;
    this.method = method || 'GET';
    this.defaultParam = param;
    this.contentType = contentType || 'application/x-www-form-urlencoded';
};
com.toutiao.core.api.singleApi.prototype.do=function (param,success,error) {
    var params = $.extend({},this.defaultParam,param);
    if(this.xhr){
        com.toutiao.core.api.abort(this.xhr);
    }
    this.xhr = $.ajax({
        cache:false,
        url:this.url,
        data:param,
        dataType:'json',
        type:this.method,
        contentType: this.contentType,
        timeout:20000,
        traditional:true
    }).done(function( data, textStatus, jqXHR ) {
        if(data["code"]!='success'){
            if(data["code"]=='no-login'){
                var event = $.Event('router:no-login')
                $(document).trigger(event)
            }
            $toutiao.tips.error(data["code"],data['msg']);
            return;
        }
        if(success){
            success(data["data"]);
        }
    }).fail(function( jqXHR, textStatus, errorThrown ) {
        // console.error(jqXHR, textStatus, errorThrown)
        $toutiao.tips.error(textStatus, errorThrown);
        if(error){
            error(jqXHR, textStatus, errorThrown);
        }
    });
};

/**
 * 上传图片API，上传格式为FormData
 * @param url
 * @param method
 * @param param
 * @author wushoulei
 * @Date 2017/11/27
 */
com.toutiao.core.api.uploadApi= function (url,method,param) {
    this.url = url;
    this.method = method || 'GET';
    this.defaultParam = param;
};
com.toutiao.core.api.uploadApi.prototype.do=function (param,success,error) {
    // var params = $.extend({},this.defaultParam,param);
    if(this.xhr){
        com.toutiao.core.api.abort(this.xhr);
    }
    this.xhr = $.ajax({
        cache:false,
        url:this.url,
        data:param,
        dataType:'json',
        type:this.method,
        processData: false,
        contentType: false,
        timeout:com.toutiao.core.api.timeout,
        traditional:true
    }).done(function( data, textStatus, jqXHR ) {
        if(data["code"]!='success'){
            $toutiao.tips.error(data["code"],data['msg']);
            return;
        }
        if(success){
            success(data["data"]);
        }
    }).fail(function( jqXHR, textStatus, errorThrown ) {
        // console.error(jqXHR, textStatus, errorThrown)
        $toutiao.tips.error(textStatus, errorThrown);
        if(error){
            error(jqXHR, textStatus, errorThrown);
        }
    });
};

com.toutiao.core.api.listApi= function (url,method,param) {
    this.url = url;
    this.method = method || 'GET';
    this.defaultParam = param;
};
com.toutiao.core.api.listApi.prototype.do=function (param,success,error) {
    var params = $.extend({},this.defaultParam,param);
    if(this.xhr){
        com.toutiao.core.api.abort(this.xhr);
    }
    this.xhr = $.ajax({
        cache:false,
        url:this.url,
        data:params,
        dataType:'json',
        type:this.method,
        timeout:com.toutiao.core.api.timeout,
        traditional:true
    }).done(function( data, textStatus, jqXHR ) {
        var res;
        var pageNum;
        var pageSize;
        var total;
        try{
            if(data["code"]!='success'){
                if(data["code"]=='no-login'){
                    var event = $.Event('router:no-login')
                    $(document).trigger(event)
                }
                $toutiao.tips.error(data["code"],data['msg']);
                return;
            }
            res=data["data"]["data"];
            pageNum = data["data"]["pageNum"];
            pageSize = data["data"]["pageSize"];
            total = data["data"]["total"];
        }
        catch (e){
            $toutiao.tips.error('错误','数据解析失败');
        }
        if(success){
            success(res,pageNum,pageSize,total);
        }
    }).fail(function(jqXHR, textStatus, errorThrown ) {
        $toutiao.tips.error(textStatus,errorThrown);
        if(error){
            error(jqXHR, textStatus, errorThrown);
        }
    });
};
