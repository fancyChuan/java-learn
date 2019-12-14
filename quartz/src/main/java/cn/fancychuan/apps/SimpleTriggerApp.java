package cn.fancychuan.apps;

import cn.fancychuan.quickstart.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleTrigger的使用
 *
 * 知识点:
 *  1. org.quartz.DateBuilder.nextGivenSecondDate(new Date(), 15); // 当前时间15秒后
 */
public class SimpleTriggerApp {

    public static void main(String[] args) throws SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory(); // 调度工厂

        Scheduler scheduler = factory.getScheduler();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

        Date startTime = DateBuilder.nextGivenSecondDate(new Date(), 15); // 当前时间15秒后
        SimpleTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10) // 每10秒执行一次
                        .withRepeatCount(5) // 一共执行5次
                ).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
