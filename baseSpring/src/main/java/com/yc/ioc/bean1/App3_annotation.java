package com.yc.ioc.bean1;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App3_annotation {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext( AppConfig.class) ;
        Student s = (Student) ac.getBean("student") ;
        System.out.println(s);

    }

}
