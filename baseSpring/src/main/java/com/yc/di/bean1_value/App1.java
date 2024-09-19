package com.yc.di.bean1_value;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.yc.di.bean1_value")
public class App1 {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(App1.class);
        Product product  = (Product) ac.getBean("product");
        System.out.println(product);

    }

}
