package zhongfucheng.controller;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * 将常用的数据抽取到该通用的Controller中
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
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

    @Autowired
    protected Scheduler sche;


    //索引库的名字和类型
    protected String indexName = ReadPropertiesUtil.readProp("indexName");
    protected String type = ReadPropertiesUtil.readProp("type");


    /**
     * 通用的跳转页面的方法
     *
     * @param folder 文件夹
     * @param file   具体文件
     * @return 具体的页面
     */
    @RequestMapping("/goURL/{folder}/{file}.do")
    public String goURL(@PathVariable("folder") String folder, @PathVariable("file") String file) {

        return "/" + folder + "/" + file + ".ftl";
    }


    /**
     * @return 返回配置项目的路径：http://localhost:8080/zhognfucheng
     */
    public String getProjectPath() {
        return ReadPropertiesUtil.readProp("projectPath");
    }

    /**
     * 根据模版名和参数生成html页面
     *
     * @param ftlName  模版名称
     * @param fileName html名称
     * @param map      参数数据
     * @throws Exception
     */
    public void createCommonHtml(String ftlName, String fileName, Map<String, Object> map) throws Exception {

        FreeMarkerUtils markerUtils = new FreeMarkerUtils();
        markerUtils.ouputFile(ftlName, fileName, map);

    }


}
