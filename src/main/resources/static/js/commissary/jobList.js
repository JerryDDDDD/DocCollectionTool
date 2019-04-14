layui.use(['table', 'layer'], function () {
    var table = layui.table;
    var layer = layui.layer;
    var $ = layui.jquery;

    table.render({
        elem: '#jobList'
        , url: '/commissary/getJobList'
        , cellMinWidth: 30 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , title: '拍卖师列表'
        , id: 'jobListTable'
        , toolbar: true
        , page: {
            layout: ['prev', 'page', 'next', 'skip', 'limit', 'count'] //自定义分页布局
            , curr: 1 //设定初始在第 1 页
            , groups: 1 //只显示 1 个连续页码
            , first: true //不显示首页
            , last: true //不显示尾页
        }
        , limits: [10, 20, 30]
        , limit: 10
        , cols: [[
            {field: 'id', title: 'ID', width: '5%', unresize: true, hide: true},
            {field: 'jobName', title: '作业名称', unresize: true},
            {field: 'description', title: '作业描述', unresize: true},
            {field: 'createTime', title: '创建时间', unresize: true},
            {field: 'jobClass', title: '所属班级', unresize: true},
            {fixed: 'right', title: '操作', toolbar: '#toolBar', unresize: true}
        ]]
        , page: true
        , request: {
            pageName: 'pageNum' //页码的参数名称，默认：page
            , limitName: 'pageSize' //每页数据量的参数名，默认：limit

        }
        , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": res.data.code, //解析接口状态
                "count": res.data.count, //解析接口状态
                "msg": res.data.msg, //解析提示文本
                "data": res.data.data//解析数据列表
            };
        },
        text: {none: '暂无拍品'},
        //表格加载完成之后按需 更改表格显示内容
        done: function (res) {
            console.log(res);
            $("[data-field='linkPic']").children().each(function () {
                if ($(this).text().search("http") != -1) {
                    var url = $(this).text();
                    $(this).empty();
                    $(this).append("<img src='" + url + "'>");
                }
            });

            $("[data-field='display']").children().each(function () {
                switch ($(this).text()) {
                    case "0": {
                        $(this).text("显示");
                        break;
                    }
                    case "1": {
                        $(this).text("不显示");
                        break;
                    }
                }
            });
        },
    });
    table.on('tool(toolBar)', function (obj) {
        var data = obj.data;
        switch (obj.event) {
            // 查看拍品公告更多信息
            case "detail": {
                // layer.confirm('您确定要删除该友情链接:吗?', {
                //     btn: ['确定', '取消'] //按钮
                // }, function (index) {
                //     //发送删除请求到后台
                //     $.ajax({
                //         url: "/admin/friendLink/delFriendLink",
                //         type: "post",
                //         data: {
                //             friendLinkId: data.id
                //         },
                //         async: false,
                //         success: function () {
                //             table.reload('friendLinkTable');
                //         },
                //         error: function () {
                //
                //         }
                //     });
                //     layer.close(index);
                // }, function () {
                //     return;
                // });
                break;
            }
            case "deadLine": {
                layer.confirm('确定停止'+ data.jobName +"收集吗?", {
                    btn: ['确定', '取消'] //按钮
                }, function (index) {
                    //发送删除请求到后台
                    $.ajax({
                        url: "/commissary/deadLine",
                        type: "post",
                        data: {
                            jobId: data.id
                        },
                        async: false,
                        success: function (res) {
                            layer.msg(res.msg);
                        },
                        error: function () {

                        }
                    });
                    layer.close(index);
                }, function () {
                    return;
                });
                break;
            }
            case "packageFile": {
                $.ajax({
                    url: "/commissary/packageFile",
                    data: {
                        jobId: data.id
                    },
                    type: "post",
                    success: function (res) {
                        layer.msg(res.msg);
                    }
                });
                break;
            }
            case "download": {
                // $.ajax({
                //     url: "/commissary/download",
                //     data: {
                //         jobId: data.id
                //     },
                //     type: "post"
                // });
                window.open("/commissary/download?jobId="+data.id);
                break;
            }
        }
    });
});