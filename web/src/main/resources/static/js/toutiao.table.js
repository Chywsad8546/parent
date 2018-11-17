(function ($) {
    function table(t,opts) {
        this.pageNum=1,
        this.t=t;
        this.opts=$.extend({pagesize:10},opts);
        this.render();
    };
    table.prototype.showloading=function(){
        this.t.block({
            message: '<h4><img src="/static/plugins/images/busy.gif" /> 加载中...</h4>',
            css: {
                border: '1px solid #fff'
            }
        });
    };
    table.prototype.hideloading=function(){
        this.t.unblock();
    };
    table.prototype.load=function(rows,total,pageindex){
        this.pageNum=pageindex;
        this.renderBody(rows);
        this.renderpage(total,pageindex);
        this.hideloading();
    };
    table.prototype.render=function(){
        var html = [];
        html.push('<div class="panel-wrapper collapse in" aria-expanded="true">\n' +
            '                    <div class="panel-body"><div class="table-responsive ">\n' +
            '<table  class="table table-bordered toutiao-table-main text-center color-bordered-table muted-bordered-table">')
        html.push('</table>\n' +
            '                        </div>\n' +
            '                        <div class="row clear toutiao-table-page"></div></div>\n' +
            '                </div>');
        this.t.html(html.join(''));
        this.renderHead();
        this.renderBody([]);
        this.renderpage(0,1);
    };
    table.prototype.renderHead=function(){
        var subtable = this.t.find('.toutiao-table-main');
        subtable.find('thead').remove();
        var theader=$('<thead></thead>');
        var tr=$('<tr></tr>');
        for(var i=0;i<this.opts["columns"].length;i++){
            var th=$('<th class="text-center"></th>');
            th.html(this.opts["columns"][i].title)
            tr.append(th);
        }
        theader.append(tr);
        subtable.prepend(theader);
    };
    table.prototype.renderBody=function(rows){
        var subtable = this.t.find('.toutiao-table-main');
        subtable.find('tbody').remove();
        rows = rows || [];
        var tbody=$('<tbody></tbody>');
        for(var r_index=0;r_index<rows.length;r_index++) {
            var row = rows[r_index];
            var tr = $('<tr></tr>');
            for (var i = 0; i < this.opts["columns"].length; i++) {
                var td = $('<td class="vm"></td>');
                var td_value="";
                try {
                    if (this.opts["columns"][i]["field"]) {
                        td_value = row[this.opts["columns"][i]["field"]];
                    }
                    else if ("render" in this.opts["columns"][i]) {
                        td_value = this.opts["columns"][i]["render"](row, r_index);
                    }
                    td.html(td_value);
                }

                catch (e){
                    console.error(e);
                }
                tr.append(td);
            }
            tbody.append(tr);
        }
        subtable.append(tbody);
    };
    table.prototype.renderpage=function (total,pageindex) {
        var that=this;
        if(!this.opts.paging){
            return
        }
        pageindex = parseInt(pageindex);
        if(pageindex<1){
            pageindex=1;
        }
        if(total<0){
            total = 0;
        }
        var totalPages=Math.floor(total/this.opts.pagesize)+((total%this.opts.pagesize)==0?0:1);
        if(totalPages==0){
            totalPages=1;
        }
        if(pageindex>totalPages){
            pageindex = totalPages;
        }
        var from = pageindex - 5;

        var to = from + 10 ;
        var buleft=0;
        var buright=0;
        if(from<1){
            to = to + Math.abs(from);
            from = 1;
            if(to>totalPages){
                to=totalPages;
            }
        }
        else if(to>totalPages){
            buleft = to-totalPages;
            to=totalPages;
            from = from - buleft;
            if(from<1){
                from=1;
            }
        }



        var pageul = this.t.find('.toutiao-table-page');
        var html=[];
        html.push('<div class="col-md-3">');
        html.push('<div class="p-t-20 m-t-5">共<span class="font-bold m-r-5 m-l-5">')
        html.push(total);
        html.push('</span>条</div>\n' +
            '        </div>');
        html.push('<div class="col-md-9">\n' +
            '            <ul class="pagination pull-right">')
        var i=0;
        html.push('<li class="page-number">',
            '<a href="javascript:void(0)" page="1">首页</a>',
            '</li>');
        html.push('<li class="page-number">',
            '<a href="javascript:void(0)" page="'+((pageindex-1)<1?1:(pageindex-1))+'">上一页</a>',
            '</li>');
        for (i = from; i <= to; i++) {
            html.push('<li class="page-number' + (i === pageindex ? ' active' : '') + '">',
                '<a href="javascript:void(0)" page="'+i+'">', i, '</a>',
                '</li>');
        }
        html.push('<li class="page-number">',
            '<a href="javascript:void(0)" page="'+((pageindex+1)>totalPages?pageindex:(pageindex+1))+'">下一页</a>',
            '</li>');
        html.push('<li class="page-number">',
            '<a href="javascript:void(0)" page="'+totalPages+'">尾页</a>',
            '</li>');
        html.push('</ul></div>')
        pageul.html(html.join(''));
        this.t.find('.pagination a').click(function (e) {
            e.preventDefault();
            if(that.opts["onPage"]){
                try{
                    that.opts["onPage"]($(this).attr('page'),that.opts.pagesize)
                }catch (e){console.error(e)}
            }
        });

    }
    $.fn.extend({
        ttTable: function (opts) {
            var that = $(this);
            if( typeof opts === 'undefined' ){
                return that.data('toutiao.ui.component.table');
            }
            if (!that.data('toutiao.ui.component.table')) {
                that.data('toutiao.ui.component.table', new table(that,opts));
            }

        }
    });
    }
)(jQuery);