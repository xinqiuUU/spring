package com.yc.zy.job;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzConfig {

    // 定义任务详情
    @Bean
    public JobDetailFactoryBean jobDetail() {
        System.out.println("jobDetail");
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();// 任务详情
        jobDetailFactory.setJobClass( HelloJob.class ); // 设置任务类
        jobDetailFactory.setDescription("Invoke Hello Job service..."); // 设置任务描述
        jobDetailFactory.setDurability(true); // 设置任务的持久性
        return jobDetailFactory;
    }

    // 定义触发器
    @Bean
    public SimpleTriggerFactoryBean trigger(JobDetailFactoryBean jobDetail) {
        System.out.println("trigger");
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();// 触发器
        trigger.setJobDetail( jobDetail.getObject() ); // 设置触发器关联的任务
        trigger.setRepeatInterval(1000); // 每5秒执行一次任务
        return trigger;
    }

    // 定义调度器
    @Bean
    public SchedulerFactoryBean scheduler(SimpleTriggerFactoryBean trigger, JobDetailFactoryBean jobDetail) {
        System.out.println("scheduler");
        SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();// 调度器
        schedulerFactory.setJobDetails(jobDetail.getObject()); // 设置调度器关联的任务详情
        schedulerFactory.setTriggers(trigger.getObject()); // 设置调度器关联的触发器
        return schedulerFactory;
    }
}
