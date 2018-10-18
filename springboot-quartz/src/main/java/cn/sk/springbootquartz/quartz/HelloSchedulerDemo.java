package cn.sk.springbootquartz.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloSchedulerDemo {
    public static void main(String[] args) throws Exception {
        //1.调度器 scheduler，从工厂中获取调度的实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2.任务实例JobDetail
        //加载任务类
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                .withIdentity("job1","group1")//参数1：任务的名称（唯一实例）；参数2：任务组的名称
                .usingJobData("msg","打印日志")//传递参数
                .usingJobData("count", 0)
                .build();

        //3.触发器Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1","group1")
                .startNow()//马上启动触发
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(5))//每五秒重复执行一次
                .usingJobData("message","simple触发器")//传递参数
                .build();

        //让调度器关联任务和触发器，保证按照触发器定义的条件执行任务
        scheduler.scheduleJob(jobDetail,trigger);
        //启动
        scheduler.start();
    }
}
