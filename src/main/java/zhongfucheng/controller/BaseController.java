package zhongfucheng.controller;

import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import zhongfucheng.service.CommentService;
import zhongfucheng.service.MemoService;
import zhongfucheng.service.UserService;
import zhongfucheng.service.impl.EmailService;
import zhongfucheng.utils.FreeMarkerUtils;
import zhongfucheng.utils.ReadPropertiesUtil;

import java.util.Map;

/**
 * Created by ozc on 2017/10/25.
 */

/**
 * 将Controller相同的内容进行抽取
 */
@Controller
public class BaseController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected EmailService emailService;


    @Autowired
    protected CommentService commentService;

    @Autowired
    protected MemoService memoService;


    //索引库的名字和类型
    protected String indexName = ReadPropertiesUtil.readProp("indexName");
    protected String type = ReadPropertiesUtil.readProp("type");


    // TODO Spring管理的bean无法调用任务，我只能用最原始的方法来new一个了....

    // TODO 再次测试是否无法用spring的对象

    //protected SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();

    @Autowired
    protected Scheduler sche;


    /**
     * 通用的页面跳转
     *
     * @param folder 模块
     * @param file   具体文件
     * @return
     */
    @RequestMapping("/goURL/{folder}/{file}.do")
    public String goURL(@PathVariable("folder") String folder, @PathVariable("file") String file) {

        return "/" + folder + "/" + file + ".ftl";
    }


    /**
     * 返回项目的路径：http://localhost:8080/zhognfucheng
     *
     * @return
     */
    public String getProjectPath() {
        return ReadPropertiesUtil.readProp("projectPath");
    }

    /**
     * 根据模版名和参数生成html页面
     * @param ftlName 模版名称
     * @param fileName html名称
     * @param map 参数数据
     * @throws Exception
     */
    public void createCommonHtml(String ftlName, String fileName, Map<String, Object> map) throws Exception {

        FreeMarkerUtils markerUtils = new FreeMarkerUtils();
        markerUtils.ouputFile(ftlName, fileName, map);

    }


}
