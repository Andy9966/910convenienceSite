package zhongfucheng.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    protected String indexName = ReadPropertiesUtil.readProp("indexName");
    protected String type = ReadPropertiesUtil.readProp("type");
    protected String type3 = ReadPropertiesUtil.readProp("type");


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
