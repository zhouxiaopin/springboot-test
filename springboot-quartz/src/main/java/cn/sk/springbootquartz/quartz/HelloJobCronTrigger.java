package cn.sk.springbootquartz.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJobCronTrigger implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //输出当前时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = simpleDateFormat.format(date);

        //工作内容
        System.out.println("正在进行数据库的备份工作，备份数据库的时间是："+strDate);
    }
}
