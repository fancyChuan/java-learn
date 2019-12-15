package cn.fancychuan.apps;

import cn.fancychuan.quickstart.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * DailyTimeIntervalTrigger的使用
 */
public class DailyTimeIntervalTriggerApp {
    public static void main(String[] args) throws SchedulerException {

        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("dt-job", "dt-group").build();
        DailyTimeIntervalTrigger trigger = TriggerBuilder.newTrigger().withIdentity("dt-trigger", "dt-group")
                .withSchedule(DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                        .withInterval(2, DateBuilder.IntervalUnit.SECOND) // 每2秒执行一次
                ).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
