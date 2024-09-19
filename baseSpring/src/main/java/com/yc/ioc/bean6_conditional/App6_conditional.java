package com.yc.ioc.bean6_conditional;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.yc.ioc.bean6_conditional")
public class App6_conditional {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(App6_conditional.class);

        WindowsPath path = (WindowsPath) ctx.getBean("windowsPath");
        System.out.println(path);


    }
}
