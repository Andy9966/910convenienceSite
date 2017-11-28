$(function () {
    $.ajax({
        url: path + "/user/getUser.do",
        type: "post",
        async: false,
        data: {},
        success: function (responseText) {

            if (responseText.user != null) {
                $("#loginDiv div h1").html("<font color='red'>" + responseText.user.userNickname + "</font>");
                $("#loginDiv").attr("href", "");

                $("#registerDiv").attr("href", path + "/logout.action");
                $("#registerDiv div h1").html("注销");

                $("#userNickname").val(responseText.user.userNickname);

                $("#userId").val(responseText.user.userId);
            }
        },
        error: function () {
            alert("系统错误");
        }
    })
});
