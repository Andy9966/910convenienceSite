package zhongfucheng.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import zhongfucheng.entity.Memo;
import zhongfucheng.service.MemoService;
import zhongfucheng.service.impl.EmailService;
import zhongfucheng.service.impl.MemoServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by ozc on 2017/12/6.
 */

/**
 * 定时器作业类，该类继承自job类
 */

@Controller
public class MemoJobDetail implements Job {

    @Autowired
    private EmailService emailService;
    @Autowired
    private MemoService memoService;


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {

            // 让Spring可以把成员变量注入进来。使用了这种方式后，就不能使用setter方法获取传递进来的参数了。
            // 因此下面使用getJobDataMap方式获取
            SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
            Map<String, Object> wrappedMap = jobExecutionContext.getJobDetail().getJobDataMap().getWrappedMap();
            Memo memo = (Memo) wrappedMap.get("memo");


            //发送邮件通知用户
            emailService.sendMemoEmail(memo);

            //修改状态
            memo.setState(Memo.ALREADY_SEND);
            memoService.updateByPrimaryKeySelective(memo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
