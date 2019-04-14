layui.use(['form'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    // form.on('submit(addClass)', function(data){
    //
    // });

    $("#addJobBtn").on("click", function () {
        var jobName = $("#JobName").val();
        var jobClass = $("#jobClass").val();
        var description = $("#description").val();
        $.ajax({
            url: "/commissary/addJob",
            type: "POST",
            data: {
                jobName: jobName,
                jobClass: jobClass,
                description: description
            },
            success: function (result) {
                layer.msg("添加成功");
                window.location.reload();
            },
            error: function () {
                layer.alert("上传失败", {icon: 2, time: 2000});
                window.location.reload();
            }
        });
        return false;
    });
});