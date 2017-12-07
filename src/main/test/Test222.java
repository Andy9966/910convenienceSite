import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.Scheduler;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import zhongfucheng.entity.Memo;
import zhongfucheng.quartz.MemoJobDetail;
import zhongfucheng.quartz.QuartzManager;
import zhongfucheng.utils.Base64Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ozc on 2017/11/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-mvc.xml","/application-*.xml"})
public class Test222 {

    @Test
    public void add() throws Exception {

        Memo memo = new Memo();
        memo.setUserId("1");
        memo.setMemoId("1");
        memo.setSendTime(new Date());
        memo.setMemoContent("this is my memo ");

        String job_name = "动态任务调度";
        String trigger_name = "动态任务调度触发器";
        System.out.println("【系统启动】开始(每1秒输出一次)...");



    }
}
