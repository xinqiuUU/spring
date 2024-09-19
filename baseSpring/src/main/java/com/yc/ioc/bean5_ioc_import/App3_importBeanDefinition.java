package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.Apple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App3_importBeanDefinition {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig_3.class);
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        Apple apple = (Apple) ac.getBean("apple");
        System.out.println(apple);
    }
}
