package com.yc.ioc.bean7_beanDefition;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Scope("prototype") //多例 懒加载
//@Scope  //默认是singleton 单例
//@Lazy   //懒加载
public class Person implements InitializingBean, DisposableBean {

    public Person(){
        System.out.println("Person构造方法");
    }
    @PostConstruct
    public void init(){
        System.out.println("Person初始化方法");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("Person销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Person afterPropertiesSet方法");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("Person destroy方法");
    }

}
