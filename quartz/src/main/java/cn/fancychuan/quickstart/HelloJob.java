package cn.fancychuan.quickstart;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {
    public HelloJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        System.out.println(detail);
        System.out.println(Thread.currentThread().getName() + ": hello ~ " + detail.getJobDataMap().getString("name"));
    }
}
