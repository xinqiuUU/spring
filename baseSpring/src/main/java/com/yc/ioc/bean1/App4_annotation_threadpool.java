package com.yc.ioc.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ThreadPoolExecutor;

public class App4_annotation_threadpool {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ac = new AnnotationConfigApplicationContext( AppConfig2.class );


        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) ac.getBean("threadPool");
        threadPool.execute(()->{
            System.out.println("执行任务");
        });
        Thread.sleep(1000);
        threadPool.shutdown();
    }
}
