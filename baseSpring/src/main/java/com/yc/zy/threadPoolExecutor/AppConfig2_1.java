package com.yc.zy.threadPoolExecutor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@PropertySource("classpath:threadPoolExecutor.properties")
@Configuration // 声明当前类是一个配置类 从threadPoolExecutor.properties 中读取配置信息
public class AppConfig2_1 {

    @Autowired
    private Environment env;

    @Bean
    public ThreadPoolExecutor threadPool() {
        int corePoolSize = Integer.parseInt(env.getProperty("threadpool.corePoolSize"));// 核心线程池的大小
        int maxPoolSize = Integer.parseInt(env.getProperty("threadpool.maxPoolSize"));// 核心线程池的最大线程数
        long keepAliveTime = Long.parseLong(env.getProperty("threadpool.keepAliveTime"));// 线程最大空闲时间  即核心线程池中(maxPoolSize-corePoolSize)  以外  的线程空闲存在时间
        TimeUnit unit = TimeUnit.valueOf(env.getProperty("threadpool.unit"));// 线程空闲时间的单位
        int queueCapacity = Integer.parseInt(env.getProperty("threadpool.queueCapacity"));// 有界阻塞队列的容量

        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueCapacity);// 有界阻塞队列

        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,unit,workQueue);

        return executor;

    }


}
