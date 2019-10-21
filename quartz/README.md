## Quartz

核心概念；
- Job 表示一个作业，要执行的具体内容。该接口只有一个execute方法
- JobDetail 表示一个具体的可执行调度程序，job是这个可执行调度程序索要执行的内容。还包含有这个任务调度的方案和策略
- Trigger触发器，代表一个调度参数的配置，什么时候去调
- Scheduler 代表一个调度容器，可以注册多个JobDetail和Trigger。当Trigger和JobDetail组合就可以被Scheduler容调度了


运行环境：
- 可以运行嵌入在另一个独立式应用程序
- 可以应用程序服务器（比如Servlet容器）内被实例化，并且参与XA事务
- 可以作为一个独立的程序运行（自己启动一个JVM），可以通过RMI使用
- 可以被实例化，作为独立的项目集群（负载均衡和故障转移功能）用于作业的执行

#### quickstart
开发调试需要加入log4j.properties日志配置文件，否则在控制台看不到日志情况，也无法看到报错信息，无法排除任务问题。配置任务可以如下：
```
log4j.rootLogger=DEBUG,A1
log4j.appender.A1=org.apache.log4j.ConsoleAppender
log4j.appender.A1.layout=org.apache.log4j.PatternLayout
log4j.appender.A1.layout.ConversionPattern=%d %5p [%t] (%F:%L) - %m%n
```

要注意的地方：
- 具体作业实现Job接口，需要是public权限
- 需要有默认构造器，因为Quartz框架在实例化作业的时候用的是clazz.newInstance()方法
```
// org.quartz.simpl.SimpleJobFactory工厂类
public Job newJob(TriggerFiredBundle bundle, Scheduler Scheduler) throws SchedulerException {
        JobDetail jobDetail = bundle.getJobDetail();
        Class jobClass = jobDetail.getJobClass();

        try {
            if (this.log.isDebugEnabled()) {
                this.log.debug("Producing instance of Job '" + jobDetail.getKey() + "', class=" + jobClass.getName());
            }

            return (Job)jobClass.newInstance(); // 需要有默认构造方法才可以
        } catch (Exception var7) {
            SchedulerException se = new SchedulerException("Problem instantiating class '" + jobDetail.getJobClass().getName() + "'", var7);
            throw se;
        }
    }
```