package cn.fancychuan.quickstart;

import org.quartz.*;

/**
 * 知识点：
 *  1. 实现Job类，同时必须要有一个无参构造方法
 *  2. JobExecutionContext是job的运行上下文，可以获取Trigger、JobDetail、Scheduler的信息
 */
public class HelloJob implements Job {
    public HelloJob() { // 一定要有一个无参构造方法
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        JobDataMap triggerDataMap = jobExecutionContext.getTrigger().getJobDataMap();
        JobDataMap mergedDataMap = jobExecutionContext.getMergedJobDataMap();
        System.out.println("============================");
        System.out.println(Thread.currentThread().getName() + "[job]\t\t" + jobDataMap.getString("name"));
        System.out.println(Thread.currentThread().getName() + "[trigger]\t" + triggerDataMap.getString("name"));
        System.out.println(Thread.currentThread().getName() + "[merged]\t\t" + mergedDataMap.getString("name"));
        System.out.println("============================");
    }
}
