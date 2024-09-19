package com.yc.zy.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

// 定义一个任务类
public class HelloJob implements Job {

    // 实现Job接口的execute方法
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello World\t调用时间:"+context.getFireTime());
    }
}
