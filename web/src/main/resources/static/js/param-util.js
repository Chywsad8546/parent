$.namespace("com.toutiao.core.util.param");
/**
 * 把url中的query转成json
 *
 * url 参数是可选的
 * 不传的时候，自动获取mvc模式url
 * @param url
 */
//            console.log("ss",History.getFullUrl(History.getHash()));
com.toutiao.core.util.param.queryToJson=function (url) {
    if( typeof url === 'undefined' ){
        var hash = History.getHash();
        url=History.getFullUrl(hash)
    }
    var resurl = new URI(url);
    return resurl.query(true);
};
