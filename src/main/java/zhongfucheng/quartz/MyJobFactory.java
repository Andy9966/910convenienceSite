package zhongfucheng.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

/**
 *
 * 本想让spring对象能够注入JOB时使用该类的，后被MemoJobDetail替代
 * Created by ozc on 2017/12/8.
 *
 * @author ozc
 * @version 1.0
 * @deprecated 已被MemoJobDetail中的SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this)替代
 */
public class MyJobFactory extends AdaptableJobFactory {


    //这个对象Spring会帮我们自动注入进来,也属于Spring技术范畴.
    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {

        //调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);

        //进行注入,这属于Spring的技术,不清楚的可以查看Spring的API.
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
}
