layui.use(['form'], function(){
    var form = layui.form;
    var $ = layui.jquery;
    // form.on('submit(addClass)', function(data){
    //
    // });

    $("#addClassBtn").on("click", function () {
        alert("aaa");
        var formData = new FormData();
        formData.append("file", document.getElementById("roster").files[0]);
        formData.append("classNumber", $("#classNumber").val());
        formData.append("className", $("#className").val());
        $.ajax({
            url: "/commissary/addClass",
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
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