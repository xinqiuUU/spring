package com.yc.di.bean2_autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.yc.di.bean2_autowired")
public class App2_autowired {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext( App2_autowired.class);
        OrderBiz biz = (OrderBiz) ac.getBean("orderBizImpl");
        biz.makeOrder();
    }
}
