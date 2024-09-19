package com.yc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext( AppMainConfig.class);
        System.out.println("Spring容器创建完成");
    }
}
