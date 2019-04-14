layui.use('form', function () {
    var form = layui.form;
    form.render();

    // 获取学号姓名
    form.on('select(jobClass)', function (data) {
        console.log(data);
        $.ajax({
            url: "/student/getStudentNumByClass",
            data: {
                _class: data.value
            },
            success: function (res) {
                console.log(res);
                $("#studentNum").empty();
                $("#studentNum").append(res.data);
                form.render();
            }
        });
    });


    form.on('select(studentNum)', function (data) {
        console.log(data);
        $("#studentName").empty();
        $("#studentName").val(data.value.split(":")[1]);
        form.render();
    });

    $("#nextBtn").on('click', function () {
        // 获取班级作业
        var classNumber = $("#jobClass").val();
        $.ajax({
            url: "/student/getJobByClass",
            data: {
                _class: classNumber
            },
            type: "POST",
            success: function (res) {
                console.log(res);
                $("#job").empty();
                $("#job").append(res.data);
                form.render();
            },
            error: function (res) {

            }
        });
    });

    $("#uploadJob").on('click', function () {
        var jobId = $("#job").val();
        var studentName = $("#studentNum").val();
        var formData = new FormData();
        formData.append("jobFile", document.getElementById("jobFile").files[0]);
        formData.append("jobId", jobId);
        formData.append("studentName", studentName);
        $.ajax({
            url: "/student/uploadJob",
            type: "POST",
            data: formData,
            contentType: false,
            processData: false,
            success: function (result) {
                alert("提交成功");
            },
            error: function () {
                layer.alert("上传失败", {icon: 2, time: 2000});
            }
        });
    });
});