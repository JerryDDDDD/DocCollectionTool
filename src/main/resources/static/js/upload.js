$("#fileUpload").on('change', function () {
    alert("aaa");
    var formData = new FormData();
    formData.append("file", document.getElementById("fileUpload").files[0]);
    $.ajax({
        url: "/commissary/fileUpload",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,
        success: function (result) {
            alert("success");
        },
        error: function () {
            layer.alert("上传失败", {icon: 2, time: 2000});
        }
    });
});