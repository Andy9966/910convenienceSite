<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>在线弹幕</title>

    <link href="${request.contextPath}/imgs/favicon.ico" type="image/x-icon" rel="shortcut icon"/>

    <!--背景CSS-->
    <link rel="stylesheet" media="screen" href="${request.contextPath}/css/background.css">

    <!--Jquery-->
    <script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>

    <!--goEasy-->
    <script src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>

    <!--弹幕-->
    <link rel="stylesheet" href="${request.contextPath}/css/barrager.css"/>
    <script src="${request.contextPath}/js/jquery.barrager.js"></script>

    <!-- bootStrap-->
    <script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css"/>


    <!--导航条CSS-->
    <link rel="stylesheet" href="${request.contextPath}/css/navbar.css"/>


    <!--清除所有默认样式-->
    <link rel="stylesheet" href="${request.contextPath}/css/cleanDefault.css"/>

    <!--使用JS获取项目根路径-->
    <script>
        var path = "";
        $(function () {
            var strFullPath = window.document.location.href;
            var strPath = window.document.location.pathname;
            var pos = strFullPath.indexOf(strPath);
            var prePath = strFullPath.substring(0, pos);
            var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
            path = prePath ;
        });
    </script>

    <!--获取用户的身份信息，如果用户已登陆，那么将用户身份信息显示出来-->
    <script src="${request.contextPath}/js/getUser.js"></script>

</head>

<body>

<input type="hidden" id="userNickname">
<input type="hidden" id="userId">


<div id="particles-js">

    <!--导航条-->
    <#include "/common/navbar.ftl">

</div>

<footer class="footer navbar-fixed-bottom ">
    <div class="container">
        <!--发送弹幕框-->
        <div  class="row">
            <div class="col-lg-12">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="请输入弹幕..." id="inputText">
                    <span class="input-group-btn">
        <button class="btn btn-primary" type="button" id="btn">发送弹幕!</button>
      </span>
                </div>
            </div>
        </div>
    </div>
</footer>

<!--本页面涉及到的JS-->
<script type="text/javascript" src="${request.contextPath}/js/onlineBarrage.js">

</script>

<!--导航条JS-->
<script src="${request.contextPath}/js/narbar.js"></script>


<!-- 背景JS -->
<script src="${request.contextPath}/js/background/particles.js"></script>
<script src="${request.contextPath}/js/background/app.js"></script>

</body>
</html>