package com.yc.ioc.bean1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.yc.ioc.bean1")
public class AppConfig {

    @Autowired
    private ApplicationContext ac ;

    //<bean id="addr" class="com.yc.bean1.Address"></bean>
    @Bean
    public Address addr(){
        Address a = new Address();
        a.setProvince("湖南");
        a.setCity("衡阳");
        a.setDistrict("珠晖");
        return a;
    }

    //<bean id="student" class="com.yc.bean1.Student"></bean>
    @Bean
    public Student student(){
        Student s = new Student();
        s.setId(1001);
        s.setName("张三");
//        s.setAddress(  addr() ); //这两句是一个意思  即都是从spring容器中取 addr对象
        s.setAddress( (Address) ac.getBean("addr") );
        return s;
    }


}
