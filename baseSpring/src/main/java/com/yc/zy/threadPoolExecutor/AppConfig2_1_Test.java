package com.yc.zy.threadPoolExecutor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

public class AppConfig2_1_Test {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ac = new AnnotationConfigApplicationContext( AppConfig2_1.class );

        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) ac.getBean("threadPool");
        threadPool.execute(()->{
            System.out.println("执行任务");
        });
        Thread.sleep(1000);
        threadPool.shutdown();
    }
}
