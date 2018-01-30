# 910便利网 #


编写网站时的记录教程：[https://zhongfucheng.bitcron.com/tag/%E4%BB%8E%E9%9B%B6%E5%BC%80%E5%8F%91%E9%A1%B9%E7%9B%AE](https://zhongfucheng.bitcron.com/tag/%E4%BB%8E%E9%9B%B6%E5%BC%80%E5%8F%91%E9%A1%B9%E7%9B%AE)(推荐

线上地址：[http://www.zhongfucheng.site/](http://www.zhongfucheng.site/)



## 如何使用 ##

1. git clone https://github.com/ZhongFuCheng3y/910convenienceSite.git
1. 打开 IDEA --> File --> New --> Open
1. 打开system.properties，配置Tomcat的项目路径projectPath,默认为http://localhost:8089
1. 打开application-context.xml,替换数据源，配置自己的mysql库，用户名和密码
2. 运行project.sql的脚本文件到自己的mysql库中
3. 添加WEB-INF下的lib包到项目下(如果没用到Elasticsearch的shield就可以把相关的代码删除了)
1. 配置Tomcat并启动
1. 输入http://localhost:8089就可以看到首页了。
1. 如果需要用到"个人收藏夹"的功能，那么就需要Elasticsearch相关的环境了。(我是用2.3.3版本的)
2. 连接Elasticsearch的代码在EsUtilsPro.java中。(我使用了shield管理Elasticsearch的，如果没有可删除用户名和密码，同时，节点名需要换成是自己的节点名才能使用)


看到有xxxx的，就是需要替换的了。




# 网站功能 #


网站的相关技术介绍可以查看上面的博文地址。也可以在线上的页面上查看[http://www.zhongfucheng.site/goURL/about/toAboutSite.do](http://www.zhongfucheng.site/goURL/about/toAboutSite.do)



本网站有两个实用的功能：**“个人收藏夹”和“个人备忘录”**


## 个人收藏夹 ##


增加一些常用的网站到Elasticsearch索引库中，以别名的方式存储起来。
![](https://i.imgur.com/xLPji9x.png)


当需要用到相关的网站的时候，在搜索栏上使用**自己起的别名**来进行索引。那么就会根据给出的别名索引出相关的记录：

![](https://i.imgur.com/iCchSkp.png)

## 个人备忘录 ##

个人备忘录则设置某时某刻需要做什么，那么就会发送邮件提醒你。

![](https://i.imgur.com/WN0aVKK.png)

## 在线聊天 ##

此功能其实还是做得非常简陋的，能够在网页上在线聊天。当你发送消息的时候，在同一时刻时，别人也可以看到你发送到的消息。

对话时以弹幕的形式发送的。

![](https://i.imgur.com/Nw8svz7.png)












