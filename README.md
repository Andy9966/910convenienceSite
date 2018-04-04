# 910便利网 #


编写网站时的记录教程：[https://zhongfucheng.bitcron.com/tag/%E4%BB%8E%E9%9B%B6%E5%BC%80%E5%8F%91%E9%A1%B9%E7%9B%AE](https://zhongfucheng.bitcron.com/tag/%E4%BB%8E%E9%9B%B6%E5%BC%80%E5%8F%91%E9%A1%B9%E7%9B%AE)(推荐

线上地址：[http://www.zhongfucheng.top/](http://www.zhongfucheng.top/)



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

2018年1月30日20:27:26，刚自己跟着上面试了一下，是可以成功启动项目的。对了，**使用是的JDK1.7**




# 网站功能 #


网站的相关技术介绍可以查看上面的博文地址。也可以在线上的页面上查看[http://www.zhongfucheng.site/goURL/about/toAboutSite.do](http://www.zhongfucheng.top/goURL/about/toAboutSite.do)



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

# 杂聊 #

编写记录:

[从零开始写项目第一篇【搭建环境】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484105&idx=2&sn=6c4c3f40489804efca12a2023df69226&chksm=ebd743c8dca0cade2d07ef36e6296c82d17f8dbeea552db71a6fbe90d33600f350e19aa1d36d#rd)

[从零开始写项目第二篇【登陆注册、聊天、收藏夹模块】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484105&idx=3&sn=eabfc5f350b5684fe8bfa561cd5164ac&chksm=ebd743c8dca0cadeb2c6a81e3f19e911ad7a52b2c5e98ea8310becefc9e3d17985a383c83a32#rd)

[从零开始写项目第三篇【在线聊天和个人收藏夹】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484105&idx=4&sn=45ff7f185ff8192a99a6421d92e3f916&chksm=ebd743c8dca0cade069a06b8ae038caa0b75ff13ac7a2b8b66fe3a4ed6fdc9a78f1f86a404e9#rd)

[从零开始写项目第四篇【搭建Linux环境】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484105&idx=5&sn=da9aaeb093adf1cfa6cda9e65defd8bc&chksm=ebd743c8dca0cade6ad7f520948a000510c608bbde16a71a6d7e9d374f62672073b07041a081#rd)

[从零开始写项目第五篇【评论功能、备忘录】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484105&idx=6&sn=9996db4b394e7578f5a44452f487ef79&chksm=ebd743c8dca0cade9c87ce2e99d946a92a575f56b2693fb733ed81a07951381a7cf5c7ab91c8#rd)

[从零开始写项目终极【维护网站、修复Bug】](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484105&idx=7&sn=2dccb666fc59cf29ea865d1fef4b7f7c&chksm=ebd743c8dca0cade4a8c84224d830cc0275837c07c3f2859892750fba979e7c23382a15d3771#rd)



## 一、功能介绍 ##

巴巴拉拉地说了一大堆东西，我们来看一下这个项目有什么功能～


这是首页，会根据当地给出三天的天气预报：

![](https://i.imgur.com/pL4Joh1.png)

很简单的注册页面，通过邮箱来进行注册～

![](https://i.imgur.com/MiUCcsu.png)

2018年3月30日15:51:23，好吧，写这篇博文的时候也出错了.....

> org.springframework.mail.MailAuthenticationException: Authentication failed


我怀疑是授权发送邮箱的密码错了(过时),于是我去更新了一下，果然就行了～

此时点击注册，就会要邮箱校验了～

![](https://i.imgur.com/XlqHUA5.png)

![](https://i.imgur.com/Df9UNnr.png)

去自己的邮箱查看一下邮件：

![](https://i.imgur.com/NwOJPf2.png)

点击校验它即可完成注册(**如果不校验的话，那么是不允许登陆的**)：


该项目用Shiro做了权限认证的，如果没在登陆的状态下点击以下的菜单都会跳转到登陆页面～：

![](https://i.imgur.com/IkTn6T3.png)


----------

**网站说明：**

这个网站的功能并不复杂，开发的功能都是为了方便使用而已。

- 一、个人收藏夹：将常用的网站添加进去，值得注意的地方就是：url（网址）最好是从浏览器复制下来，这是最方便也是最安全的做法
- 二、个人备忘录：添加个人备忘录，设置好时间、在邮件发送出去之前都能够修改时间

![](https://i.imgur.com/2q4VTd3.png)

![](https://i.imgur.com/wKZJZfS.png)

发送邮件好像会有点延迟～～～～

![](https://i.imgur.com/N77tvQD.png)


----------

接下来是个人收藏夹了，**添加一些常用的网址进去，并给它命名，当输入命名部分的字符时，即会提示**：

![](https://i.imgur.com/CJx0aHT.png)


![](https://i.imgur.com/G2E090B.png)

![](https://i.imgur.com/mrwhaee.png)


用键盘**上下键选中按回车即可**跳转到对应的页面了～


## 二、技术介绍 ##


**网站用到的技术：**

- 一、全站页面布局：前端是使用Bootstrap来进行布局的、背景是使用了GitHub的一个开源项目：particles-js。导航条来源于一个国外网站：http://toolofna.com/
- 二、首页：天气预报功能来源于高德地图API和和风天气API组合而成，能够查看当前IP地址所在地的近三日天气
- 三、个人收藏夹：使用了Elasticsearch全文搜索引擎工具。
- 四、在线聊天：使用了GoEasy的服务推送和jquery.barrager.js构建弹幕。目前该功能并未完善，敬请期待。
- 五、注册和登陆：使用了BootstrapValidation进行表单校验、JavaMail发送邮件的功能、Gif动态验证码
- 六、个人备忘录：使用了Quartz任务调度工具来定时发送邮件


**总概要：**

- &nbsp;&nbsp;&nbsp;1. Maven构建项目
- &nbsp;&nbsp;&nbsp;2. 使用Mysql数据库
- &nbsp;&nbsp;&nbsp;3. Tomcat作用应用服务器
- &nbsp;&nbsp;&nbsp;4. Dao层采用Mybatis，Controller层采用SpringMVC，Spring对Mybatis和SpringMVC进行整合和事务管理
- &nbsp;&nbsp;&nbsp;5. Shiro权限管理框架控制登陆以及对个人收藏夹、在线聊天功能进行认证的管理
- &nbsp;&nbsp;&nbsp;6. 使用FreeMarker来渲染页面和配置发送邮箱的模版
- &nbsp;&nbsp;&nbsp;7. 使用Quartz和Spring整合，完成个人备忘录的功能。
- &nbsp;&nbsp;&nbsp;8. 使用Nginx作为代理服务器，对静态资源处理，代理转发


可以看到的是，网站的功能并不多，就几个小模块，我就创建了三张表而已:

![](https://i.imgur.com/3FK41pF.png)

## 三、谈谈编写过程 ##

其实所有的初学者都一样：怎么使用Java编写一个网站？数据库设计感觉好难，设计错了就很麻烦了。Web前端好烦啊，自己写的页面不好看。这个功能感觉好难实现哦，应该是挺高深的技术吧。我的基础还不够扎实，等我学好了再写吧，等等等..就一直有想写网站的念头，但一直没开始动手。

上面的问题我都有想过，不过我还是写了这么一个小东西....


当时写的练手项目都是视频教程给出的题目，比如我之前发过的：[移动商城项目总结](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484052&idx=1&sn=379f23d7d759658eb1d85cc5363b84a0&chksm=ebd74395dca0ca8351ba28b299afbf2ecb6a40338f62b05cfe8faa0e5b53cfd67926aed43530#rd)和[纳税服务系统总结](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484000&idx=1&sn=dd8b96b94942001150143c877a773f53&chksm=ebd74361dca0ca7796bd25f176697692d46025cfeb37cb6a23ad4617ef0f74c9b76633379681#rd)，jar包或者maven的坐标都是有给出来的，项目的前台页面和数据库标也大都是设计好的，我只需要补补填填写Java代码。而这一次，前端页面，数据库设计，Maven坐标等等全由自己包办


从开始到编写结束大概花了我一个月的时间，期间要去上课呀，回家呀，一些琐事呀等等才拖了那么久。如果自己从来没写过项目的同学建议自己折腾一下搞一个，期间能学到很多很多东西的。也不要怕自己写得不好(只要像我一样不要脸，不也**将这么一个小东西**发到公众号上来，发到其他的博客平台了么)


那么我自己折腾这么一个小玩意能学到什么了呢？

### 3.1搭建环境收获 ###


- 在搭建环境的时候，**不要觉得最新的版本就是牛逼**，我要用最新的，不做过时的男人！我当时就是撘环境是用Spring5.0，Mysql驱动包6.x，jdk1.8，能用最新就用最新的，觉得自己贼牛逼。然后**撘完项目就启不动了**～～～
- 其实没必要用最新的，最新的一般都会有bug，没bug的也不一定兼容其他的版本。要知道，Spring可不单单有Spring的东西，还依赖很多其他的jar包的。同样Mysql用最新的驱动包，逆向工程时也不好使(我在SegementFault已经见过几次跟我一样遇到的坑)
- 一般最新的东西资料都挺少的，除了官方文档，出了什么问题都只能自己解决了。较老的版本就不一样了，很多人都会遇到的问题，解决方案也会相当多～



当时我在图书馆撘了一个晚上，得出的教训：

![](https://i.imgur.com/5QjrVtl.png)

### 3.2登陆与注册收获 ###

登陆与注册这个功能只要学过JavaWeb基础的同学，肯定做过。反正我是做了很多很多次的了，学Servlet做一次，学JSP做一次，学AJAX做一次，学xxx系统做一次，学XXX商城做一次。反正每次都会有新的东西补充上去


一开始我本来想做的是可以使用微信登陆，也可以使用账户(邮箱)登陆，也可以使用短信登陆的。

后来发现微信登陆要企业认证，做不了。短信登陆要钱，不想给。

所以最后就只有用邮箱登陆了~~~当然了，**该有的基本功能是有的**：

- 加密密码
- 前台校验和后台校验参数
- 忘记密码，记住我功能
- 验证码


由于之前是简单学过Shiro的，当时觉得Shiro做权限很方便，于是自己也想体验一把～实际上用起来并没有我当时学的时候那么简单！


在Shiro这一块也耽误了不少时间，因为我当时想的是使用AJAX来提交，如果正确则跳转到首页，如果密码(参数..)等错误就返回JSON给用户一个友好的提示。可是，Shiro的表单认证起默认返回的是一个页面，在这里就卡住了挺久的(有的时候没bug，有的时候又有bug)，百思不得其解...最后查阅了很多资料才将FormAuthenticationFilter改写好，Shiro很多东西都是要自己重写来进行控制的，当时理解后画的流程图：

![](https://i.imgur.com/zt7Sb5p.png)


Shiro的“记住我”功能本来以为是很好用的，实则也挺鸡肋的..当时我做的记录：

![](https://i.imgur.com/0cbtKFN.png)


反正Shiro给我的感觉就是很多东西都要自己来重写，控制，而如果是小范围使用我觉得自己写url控制会方便。也可能是我的学习姿势不对～

在登陆注册模块期间也自己去找了不少资料。

- 数据库设计上也是参考了网上和自己做了些许修改
- JavaMail我之前是没接触过的
- BootStrapValidation也是看中了就用，
- 折腾Shiro几天就更不用说了。


所以说，未必要使用自己熟悉的东西，**只要想实现一些普通、你见过的功能，总会有方案可以参考**。


### 3.3个人收藏夹收获 ###


这个自动补全功能是我一开始想要做自己网站的时候想做的。因为我习惯了使用键盘去找文件，去找东西。

比如，在windows下，我下了一个rolan，将常用的软件丢进去，输入一些关键字即提示我，然后我通过键盘上下选择就可以打开软件了，十分方便


在编写之前，我学过Lucene的相关知识，知道Lucene是全文搜索索引的始祖(没做过相关案例)【参考博文：[Lucene就是这么简单](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484022&idx=2&sn=ca972fb77eb8f3a69d20071afeb359b3&chksm=ebd74377dca0ca612ea6966a55bb66444383226f08cbdcaa2805507aba706089a9ab689a1de7#rd)】，后来知道Solr和Elasticsearch在企业上是用得挺火的，而Elasticsearch又是各种火热，各种吹(哈哈哈，无贬义，是挺好用的)。

于是我就花了几天从零开始学Elasticsearch，当时参考的博主给出的是2.3.3版本，我也觉得没什么，就使用了。那是最新是5.x～就有了后来的博文：【[Elasticsearch就是这么简单](https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484058&idx=2&sn=5c2b32adaab2ffe4b331c5abb95a5f43&chksm=ebd7439bdca0ca8d7967fcaa037fcd7880cfbd6b36478f51617ad9de79040076e2f72dbad8ba#rd)】

Elasticsearch遇到很多的问题都得去翻英文的，所以可以访问外网也挺重要的～

我英语也不咋地，就慢慢看看，弄个Chrome插件翻译呗，事情总得解决，也就这么成长的～



### 3.4搭建Linux环境收获 ###

在之前只会Linux一些非常简单的命令，`ls  cd vi `这些，用得少就忘记了，于是又得重新去回顾一下Linux(这个学期也开了Linux的课，我打算过一阵再总结Linux的常用命令和相关要点，敬请期待～)

花了点点钱买了台云服务器(学生有优惠就10块一个月)，我也建议是在校的学生也可以买来自己玩玩，熟悉一下Linux命令，还有很多软件都在Linux下才更好地支持(例如：Elasticsearch，Nginx)，尤其Elasticsearch在windows下装它简直麻烦！


到了Linux阶段，我也去学了下Nginx了。在没学Nginx时我就经常听说过这个HTTP服务器了，但一直不知道它是干嘛用的。它的最常见作用就两个：

- 处理静态资源
- 动态资源转发到Web服务器(Tomcat)

学学Nginx的配置文件相关知识，是如何配置的，配置有什么用～就可以使用了


### 3.5评论功能和备忘录收获 ###


评论功能在数据库设计上也有好几种：

- 将回复和评论都统一当成是评论，能够使用@XXX来当做是回复，类似与IOS知乎的评论【此做法是最简单的】
- 将回复和评论分开，要么单表就多增加一个父属性，要么就分两张表【稍微复杂，一个评论可以对应多个回复】
- 实现评论与回复“盖楼”的方式，这种就是网易的评论【此种是最复杂的】


我做的只是一个留言的功能，就直接使用第一种了～

对于备忘录就得用到Quartz时间调度框架，之前接触过，但用的使用还是出现了Bug

- 在Junit测试Quartz没有反应
- Spring不能注入对象进Quartz的Job类中

如果业务简单的话，下次也想玩玩Spring自带的任务调度～


## 四、最后 ##


上面简要地介绍了我的小项目的功能和编写过程，更详细的业务得去看我下面的博文和源码了～也希望大家能够有所收获，自己动手做一个小项目～


现在看来很多没有写成规范(返回JSON、JavaScript模块化)等等，但毕竟是自己学编程的成长经历，看回以前的代码总会有这种感觉。我短时间内也不做重构了，学Java基础去～

后面的博文几乎都是Java基础或算法(少量)了，希望大家多多支持哈，在校的日子我会尽量保持日更的～

![](https://i.imgur.com/KpJWn4u.jpg)




> 如果文章有错的地方欢迎指正，大家互相交流。习惯在微信看技术文章，想要获取更多的Java资源的同学，可以**关注微信公众号:Java3y**














