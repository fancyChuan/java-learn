## Quartz的使用

调度器通过SchedulerFactory创建，scheduler实例化后，可以启动(start)、暂停(stand-by)、停止(shutdown)

### 1. Quartz API关键接口
- Scheduler - 与调度程序交互的主要API。
- Job - 由希望由调度程序执行的组件实现的接口。
- JobDetail - 用于定义作业的实例。
- Trigger（即触发器） - 定义执行给定作业的计划的组件。
- JobBuilder - 用于定义/构建JobDetail实例，用于定义作业的实例。
- TriggerBuilder - 用于定义/构建触发器实例。


调度器类型
```
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.CalendarIntervalScheduleBuilder.*;
```


Quartz自带了各种不同类型的Trigger，最常用的主要是
- SimpleTrigger：
    - 用于一次性的任务，比如在某个时间点执行一次
    - 任务在特定时间点执行，重复N次，每次间隔T个事件定位
- CronTrigger：基于日历的调度


Job和Trigger都可以设置Key（由name和group组成），分别为JobKey和TriggerKey，这些key可以将job和trigger放到不同的分组中，然后基于分组进行操作

### 2.Job和JobDetail

scheduler调度job的过程：
- 创建job的一个实例
- 调用job的execute方法
- 执行完毕后，对该实例的引用就会被丢弃，实例被垃圾回收。这种策略带来的问题：
    - job中必须有一个无参的构造方法（当使用默认的JobFactory时）
    - 在Job类中，不应该定义有状态的数据属性，因为job的多次执行中，这些属性的值不会传递

#### JobDataMap
- 用于给job实例增加属性或者配置或者在job的多次执行中跟踪job的状态
- 可以包含不限量的序列化的数据对象
    - 也就是说使用JobDataMap，那么里面的对象都会被序列化，使用java的基本类型不会有问题，但是如果使用某个类的实例化对象，那么就可能引起类版本不一致的问题。
    - 可以配置JDBC-JobStroe和JobDataMap使得map中仅允许存储基本类型和String类型的数据，避免序列化问题
- 写入jobDataMap，在JobDetail和Trigger都可以设置，后者覆盖前者
```
JobDetail job = JobBuilder.newJob(HelloJob.class) 
        .withIdentity("job1", "group1")
        .usingJobData("name", "quartz-fancy")
        .build();
SimpleTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity("trigger1", "group1")
        .usingJobData("name", "from trigger") 
```
- 使用：有三种方式，分别从JobDetail获取、从Trigger中获取、直接从上下文中获取合并后的JobDataMap
```
JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
JobDataMap triggerDataMap = jobExecutionContext.getTrigger().getJobDataMap();
JobDataMap mergedDataMap = jobExecutionContext.getMergedJobDataMap(); // trigger中如果存在跟job同名的变量，那么会trigger会覆盖job的
```