package cn.fancychuan.apps;

import cn.fancychuan.quickstart.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * CalendarIntervalTrigger的使用
 */
public class CalendarIntervalTriggerApp {
    public static void main(String[] args) throws SchedulerException {
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();

        JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("c-job", "c-group").build();
        CalendarIntervalTrigger trigger = TriggerBuilder.newTrigger().withIdentity("c-trigger", "c-group")
                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                        .withInterval(2, DateBuilder.IntervalUnit.SECOND) // 每2秒执行
                ).build();

        scheduler.scheduleJob(job, trigger);
        scheduler.start();
    }
}
