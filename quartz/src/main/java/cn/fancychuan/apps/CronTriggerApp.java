package cn.fancychuan.apps;

import cn.fancychuan.quickstart.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * CronTrigger的使用
 */
public class CronTriggerApp {
    public static void main(String[] args) throws SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job-cron", "group-cron").build();
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger-cron", "group-cron")
                .withSchedule(CronScheduleBuilder.cronSchedule("*/2 * * * * ?")).build(); // 每两秒执行一次

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
