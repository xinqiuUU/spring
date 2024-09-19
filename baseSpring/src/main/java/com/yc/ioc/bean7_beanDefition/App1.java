package com.yc.ioc.bean7_beanDefition;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.yc.ioc.bean7_beanDefition")
public class App1 {
    public static void main(String[] args) {
        //只创建容器的话 在prototype模式下，实例会创建吗
        ApplicationContext ac = new AnnotationConfigApplicationContext(App1.class);

        //在prototype模式下，每次获取的都是新的对象 每getBean一次创建一次对象
        Person p1 = (Person) ac.getBean("person");
        //Person p2 = (Person) ac.getBean("person");

    }
}
