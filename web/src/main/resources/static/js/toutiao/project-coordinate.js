$(function(){

    var _newCode = getQueryString('newcode');
        /*
         * 获取某个新房或二手房信息
         * */
        com.toutiao.officedict.project.getHousingProject.do({"newcode": _newCode},function(res){
            getHousingProject(_newCode,res);
        },function (jqXHR, textStatus, errorThrown) {
            console.log('error',jqXHR, textStatus, errorThrown)
        });
});

function getHousingProject(_newCode,res) {
    console.log(res);
    var getHousingProjectHtml = template('art-template', res);
    $('#get-housing-project-content').html(getHousingProjectHtml);
}