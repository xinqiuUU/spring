package com.yc.ioc.bean1;

import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;

public class AppConfig2 {

    //TODO 参数值如何处理 不能写死
    // ->属性文件  -> spring读取属性文件
    // -> 如何将spring中读入的参数值  注入 到 线程池中

    //<bean id="threadPool" class="java.util.concurrent.ThreadPoolExecutor">
    @Bean
    public ThreadPoolExecutor threadPool() {

        //核心线程池的大小
        int corePoolSize = 3;
        //核心线程池的最大线程数
        int maxPoolSize = 5;
        //线程最大空闲时间  即核心线程池中(maxPoolSize-corePoolSize)  以外  的线程空闲存在时间
        long keepAliveTime = 10;
        //空闲时间单位
        TimeUnit unit = TimeUnit.SECONDS; // enue枚举  常量
        //阻塞队列  容量为2  最多允许放入两个  空闲任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        ThreadPoolExecutor executor = null;
        //推荐的创建线程池的方式
        //不推荐使用现成的API创建线程池
        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, unit, workQueue);
        return executor;
    }
}
