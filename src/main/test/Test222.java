import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import zhongfucheng.utils.Base64Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ozc on 2017/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-mvc.xml","/application-*.xml"})
public class Test222 {

    @Autowired
    private FreeMarkerConfig freeMarkerConfig;

    @Test
    public void add() throws Exception {

        Map<String, Object> map = new HashMap();
        map.put("nickName", "nihao");
        map.put("content", 333);
        map.put("url", 333);
        map.put("encodeUrl", Base64Util.encodeData("333"));

        Template template = freeMarkerConfig.getConfiguration().getTemplate("/email.ftl");
        String s = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        System.out.println(s);


    }
}
