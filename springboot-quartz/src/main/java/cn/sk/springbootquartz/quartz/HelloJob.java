package cn.sk.springbootquartz.quartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@PersistJobDataAfterExecution//多次调用Job的时候，会对Job进行持久化，即保存一个数据信息
public class HelloJob implements Job {

    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //输出当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = simpleDateFormat.format(date);
        //获取JobDetail的内容
        JobKey jobKey = context.getJobDetail().getKey();
        System.out.println("工作任务的名称："+jobKey.getName()+"，工作任务的组："+jobKey.getGroup());
        System.out.println("工作任务类的名称："+jobKey.getClass().getName());
        System.out.println("工作任务类的名称："+jobKey.getClass().getSimpleName());
//        System.out.println("任务传递过来的参数值："+context.getJobDetail().getJobDataMap().getString("msg"));
        System.out.println("任务传递过来的参数值："+msg);
        System.out.println("任务传递过来的参数值count："+count);
        ++count;
        context.getJobDetail().getJobDataMap().put("count",count);
        //获取Trigger对象中获取jobDataMap的数据
        JobDataMap triggerDataMap = context.getTrigger().getJobDataMap();
        System.out.println("***********************************");
        System.out.println("触发器数据的参数值："+triggerDataMap.getString("message"));
        System.out.println("***********************************");

        //获取其他的内容
        System.out.println("当前任务的执行时间："+simpleDateFormat.format(context.getFireTime()));
        System.out.println("下一次任务的执行时间："+simpleDateFormat.format(context.getNextFireTime()));


        //工作内容
        System.out.println("正在进行数据库的备份工作，备份数据库的时间是："+strDate);
    }
}
