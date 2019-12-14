package cn.fancychuan.quickstart;

import org.quartz.*;

public class HelloJob implements Job {
    public HelloJob() {
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
