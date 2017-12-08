<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>关于网站</title>

    <link href="${request.contextPath}/imgs/favicon.ico" type="image/x-icon" rel="shortcut icon"/>


    <!--Jquery-->
    <script src="${request.contextPath}/js/jquery-3.2.1.min.js"></script>

    <!-- bootStrap-->
    <script src="${request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.css"/>

    <!--导航条CSS-->
    <link rel="stylesheet" href="${request.contextPath}/css/navbar.css"/>


    <!--弹出框-->
    <script src="${request.contextPath}/js/sweetalert.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/css/sweetalert.css">

    <!--使用JS获取项目根路径-->
    <script>
        var path = "";
        $(function () {
            var strFullPath = window.document.location.href;
            var strPath = window.document.location.pathname;
            var pos = strFullPath.indexOf(strPath);
            var prePath = strFullPath.substring(0, pos);
            var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
            path = prePath;
        });
    </script>

    <!--清除所有默认样式-->
    <link rel="stylesheet" href="${request.contextPath}/css/cleanDefault.css"/>

    <!--获取用户的身份信息，如果用户已登陆，那么将用户身份信息显示出来-->
    <script src="${request.contextPath}/js/getUser.js"></script>


    <!--该网页的样式-->
    <link rel="stylesheet" href="${request.contextPath}/css/toAboutSite.css"/>



    <!--该网页的JS-->
    <script src="${request.contextPath}/js/toAboutSite.js"></script>


</head>
<body>

<!--获取用户的Id-->
<input type="hidden" id="userId" name="userId">


    <!--导航条-->
<#include "/common/navbar.ftl">


    <!--网页内容-->
    <div class="content" >

        <h1>为什么要开发该网站：</h1>
        <p>学习JavaWeb也有一段时间了，学习了不少的知识，也跟着视频教程做了不少的“项目”，但从未自己真正写过属于自己的项目。</p>
        <p>写该项目就是为了把自己学过的东西来整合一遍，把学过的技术都整合进来，并且为了方便自己使用吧。</p>


        <h1>网站说明：</h1>
        <p>这个网站的功能并不复杂，开发的功能都是为了方便使用而已。</p>
        <p>一、个人收藏夹：将常用的网站添加进去，值得注意的地方就是：url（网址）最好是从浏览器复制下来，这是最方便也是最安全的做法</p>
        <p>二、个人备忘录：添加个人备忘录，设置好时间、在邮件发送出去之前都能够修改时间</p>


        <h1>网站用到的技术：</h1>
        <p>一、全站页面布局：前端是使用Bootstrap来进行布局的、背景是使用了GitHub的一个开源项目：particles-js。导航条来源于一个国外网站：http://toolofna.com/</p>
        <p>二、首页：天气预报功能来源于高德地图API和和风天气API组合而成，能够查看当前IP地址所在地的近三日天气</p>
        <p>三、个人收藏夹：使用了Elasticsearch全文搜索引擎工具。</p>
        <p>四、在线聊天：使用了GoEasy的服务推送和jquery.barrager.js构建弹幕。目前该功能并未完善，敬请期待。</p>
        <p>五、注册和登陆：使用了BootstrapValidation进行表单校验、JavaMail发送邮件的功能、Gif动态验证码</p>
        <p>六、个人备忘录：使用了Quartz任务调度工具来定时发送邮件</p>
        <p>总概要：</p>
        <p>&nbsp;&nbsp;&nbsp;1. Maven构建项目</p>
        <p>&nbsp;&nbsp;&nbsp;2. 使用Mysql数据库</p>
        <p>&nbsp;&nbsp;&nbsp;3. Tomcat作用应用服务器</p>
        <p>&nbsp;&nbsp;&nbsp;4. Dao层采用Mybatis，Controller层采用SpringMVC，Spring对Mybatis和SpringMVC进行整合和事务管理</p>
        <p>&nbsp;&nbsp;&nbsp;5. Shiro权限管理框架控制登陆以及对个人收藏夹、在线聊天功能进行认证的管理</p>
        <p>&nbsp;&nbsp;&nbsp;6. 使用FreeMarker来渲染页面和配置发送邮箱的模版</p>
        <p>&nbsp;&nbsp;&nbsp;7. 使用Quartz和Spring整合，完成个人备忘录的功能。</p>


        <h1>具体的思路以及代码：</h1>
        <p>博客记录了该网站的创建过程：https://zhongfucheng.bitcron.com/tag/%E4%BB%8E%E9%9B%B6%E5%BC%80%E5%8F%91%E9%A1%B9%E7%9B%AE</p>
        <p>该网站的GitHub地址：https://github.com/ZhongFuCheng3y/910convenienceSite</p>

        <hr>
        <hr>
        <hr>
        <hr>


        <!--查看评论区-->
        <div id="lookComment">
            <p><h1>查看评论：</h1></p>
        </div>

        <!--发布评论区-->
        <div id="comment">
            <p><h1>评论：</h1></p>
            <textarea class="form-control" rows="3" id="comment_text" style="background: black"></textarea>
            <p class="text-right">
                <button type="button" class="btn btn-primary" id="submitComment">提交评论</button>
            </p>
        </div>
    </div>

</div>


<!--导航条JS-->
<script src="${request.contextPath}/js/narbar.js"></script>



</body>
</html>