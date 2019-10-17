package cn.fancychuan.apps;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class QuickstartApp {
    public static void main(String[] args) throws InterruptedException {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // scheduler.start(); // 启动

            JobDetail job = JobBuilder.newJob(HelloJob.class) // 真正执行逻辑所在的地方
                    .withIdentity("job1", "group1")
                    .usingJobData("name", "quartz-fancy")
                    .build();

            SimpleTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow() // 一旦加入scheduler立即生效
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule() // 使用simpleScheduler
                            //.withIntervalInSeconds(2) // 每隔2秒执行一次
                            // .repeatForever() // 一直执行下去
                            .repeatSecondlyForever(2)
                            )
                    .build();

            System.out.println("定义完成，准备加入调度");
            // 加入调度
            scheduler.scheduleJob(job, trigger);
            scheduler.start();

            Thread.sleep(10000);
            scheduler.shutdown(true);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    class HelloJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            JobDetail detail = jobExecutionContext.getJobDetail();
            System.out.println(detail);
            System.out.println(Thread.currentThread().getName() + ": hello ~ " + detail.getJobDataMap().getString("name"));
        }
    }
}

