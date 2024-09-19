package com.yc.ioc.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App2_new {
    public static void main(String[] args) {

        //自动装配
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        Student s = (Student) ac.getBean("student");
        System.out.println(s);
    }
}
