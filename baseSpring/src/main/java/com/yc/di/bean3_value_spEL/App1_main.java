package com.yc.di.bean3_value_spEL;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class App1_main {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext( App1_main.class);
        Person p = (Person) ac.getBean("person");
        System.out.println(p);
    }
}
