package zhongfucheng.quartz;

import org.apache.commons.collections.map.HashedMap;
import org.quartz.*;
import zhongfucheng.entity.Memo;
import zhongfucheng.utils.Date2Cron;


import java.util.Map;

/**
 * Created by ozc on 2017/12/6.
 */
public class QuartzManager {

    private static String JOB_GROUP_NAME = "MY_JOB_GROUP";
    private static String TRIGGER_GROUP_NAME = "MY_TRIGGER_GROUP";


    /**
     * @param sched       调度器
     * @param jobClass    任务
     * @Description: 添加一个定时任务
     * @Title: QuartzManager.java
     */
    public static void addJob(Scheduler sched, @SuppressWarnings("rawtypes") Class jobClass, Memo memo) {

        //装载着参数
        Map<String, Object> map = new HashedMap();

        map.put("memo", memo);

        try {
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(memo.getMemoId(),JOB_GROUP_NAME).usingJobData(new JobDataMap(map)).build();// 任务名，任务组，任务执行类

            // 触发器
            CronTrigger trigger = TriggerBuilder
                    .newTrigger()
                    .withIdentity(memo.getMemoId(), TRIGGER_GROUP_NAME)// 触发器名,触发器组
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule(Date2Cron.getCron(memo.getSendTime())))
                    .build();

            //绑定任何和触发器，开始执行
            sched.scheduleJob(jobDetail, trigger);
            sched.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param sched       调度器
     * @Description: 修改一个任务的触发时间
     * @Title: QuartzManager.java
     */
    public static void modifyJobTime(Scheduler sched,Memo memo) {
        try {
            CronTrigger trigger = (CronTrigger) sched.getTrigger(new TriggerKey(memo.getMemoId(), TRIGGER_GROUP_NAME));
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();

            if (!oldTime.equalsIgnoreCase(Date2Cron.getCron(memo.getSendTime()))) {

                JobDetail jobDetail = sched.getJobDetail(new JobKey(memo.getMemoId(), JOB_GROUP_NAME));
                Class objJobClass = jobDetail.getJobClass();
                removeJob(sched, memo.getMemoId(),memo.getMemoId());
                addJob(sched, objJobClass,memo);

                sched.start();


            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * @param sched       调度器
     * @param jobName
     * @param triggerName
     * @Description: 移除一个任务
     * @Title: QuartzManager.java
     */
    public static void removeJob(Scheduler sched, String jobName, String triggerName) {
        try {
            sched.pauseTrigger(new TriggerKey(triggerName, TRIGGER_GROUP_NAME));// 停止触发器
            sched.unscheduleJob(new TriggerKey(triggerName, TRIGGER_GROUP_NAME));// 移除触发器
            sched.deleteJob(new JobKey(jobName, JOB_GROUP_NAME));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param sched 调度器
     * @Description:启动所有定时任务
     * @Title: QuartzManager.java
     */
    public static void startJobs(Scheduler sched) {
        try {
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param sched 调度器
     * @Description:关闭所有定时任务
     * @Title: QuartzManager.java
     */
    public static void shutdownJobs(Scheduler sched) {
        try {
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
