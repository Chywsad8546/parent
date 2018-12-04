/**
 * 获取地址栏参数-正则表达式
 * @param name
 * @returns {null}
 * @constructor
 */
function getQueryString(name){

    var _hashStr = window.location.hash;
    var _num = _hashStr.indexOf("?");

    if (_num > -1) {
        var _searchStr = _hashStr.substr(_num + 1);
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = _searchStr.match(reg);
        if(r!=null)
            return  unescape(r[2]);
        return null;
    }

    return null;
}

function getQueryStringZhongwen(name) {

    var _hashStr = window.location.hash;
    var _num = _hashStr.indexOf("?");

    if (_num > -1) {
        var _searchStr = _hashStr.substr(_num + 1);
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = _searchStr.match(reg);
        if(r!=null)
            return  decodeURI(r[2]);
        return null;
    }
    return null;
}

//添加url参数
function ChangeParam(name,value)
{
    var url=window.location.href ;
    var newUrl="";
    var reg = new RegExp("(^|)"+ name +"=([^&]*)(|$)");
    var tmp = name + "=" + value;
    if(url.match(reg) != null)
    {
        newUrl= url.replace(eval(reg),tmp);
    }
    else
    {
        if(url.match("[\?]"))
        {
            newUrl= url + "&" + tmp;
        }
        else
        {
            newUrl= url + "?" + tmp;
        }
    }
    location.href=newUrl;
}



/**
 * 下拉框渲染函数
 * 示例：
 * <select class="form-control" name="buildcategory" id="buildcategory" data-option="buildCategoryId" data-value="buildCategoryName">
 <option value="{{buildcategory}}" selected>{{buildcategory}}</option>
 </select>
 * @param item
 * @param result
 */
function renderDropdownBox(item, result) {
    var _selVal = item.val();
    var _option = item.data('option'),
        _value = item.data('value');

    var _selHtml;
    for (var i=0; i<result.length;i++) {
        var tempObj = result[i];
        if (tempObj[_option] == _selVal) {
            _selHtml += "<option value=" + tempObj[_option] + " selected>" + tempObj[_value] + "</option>";
        } else {
            _selHtml += "<option value=" + tempObj[_option] + ">" + tempObj[_value] + "</option>";
        }
        item.html(_selHtml);
    }
}

/**
 * 复选框渲染函数
 * @param item
 * @param result
 */
function renderCheckBox(item, result) {
    var _name = item.data('name');
    var _value = item.data('value');
    var _attribute1 = item.data('attribute1');
    var _attribute2 = item.data('attribute2');

    var _checkHtml = "";
    for (var i=0; i<result.length;i++) {
        var tempObj = result[i];

        if($.inArray(tempObj[_attribute1], _value) >= 0) {
            _checkHtml += "<label class='checkbox-inline'>"
                + "<input type='checkbox' checked name='" + _name + "' value='" + tempObj[_attribute1] + "'>" + tempObj[_attribute2]
                + "</label>";
        } else {
            _checkHtml += "<label class='checkbox-inline'>"
                + "<input type='checkbox' name='" + _name + "' value='" + tempObj[_attribute1] + "'>" + tempObj[_attribute2]
                + "</label>";
        }
    }
    item.html(_checkHtml);
}

/**
 * 时间格式化处理
 * @param fmt
 * @param date
 * @returns {*}
 */
function dateFtt(fmt,date) {
        var o = {
        "M+" : date.getMonth()+1,                 //月份
        "d+" : date.getDate(),                    //日
        "h+" : date.getHours(),                   //小时
        "m+" : date.getMinutes(),                 //分
        "s+" : date.getSeconds(),                 //秒
        "q+" : Math.floor((date.getMonth()+3)/3), //季度
        "S"  : date.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
}