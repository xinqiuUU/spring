package com.yc.zy.jms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JmsConfig.class);
        MessageProducer mp = (MessageProducer) ac.getBean("messageProducer");
        mp.sendMessage("Hello, World!");
//        mp.sendMessage("Hello, World!");
    }
}
