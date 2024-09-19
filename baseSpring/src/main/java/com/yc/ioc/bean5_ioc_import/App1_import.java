package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.Apple;
import com.yc.ioc.bean5_ioc_import.other.Banana;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App1_import {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(  AppConfig_1.class );
        //获取容器中所有的bean定义
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names){
            System.out.println( name );
        }
        Apple apple = (Apple) ac.getBean("a");
        System.out.println(apple);
        apple = (Apple) ac.getBean("com.yc.bean5_ioc_import.other.Apple");
        System.out.println(apple);
        Banana banana = (Banana) ac.getBean("com.yc.bean5_ioc_import.other.Banana");
        System.out.println(banana);
    }

}
