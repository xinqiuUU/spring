package com.yc.ioc.bean4_ioc_factorybean;

import com.yc.ioc.bean4_ioc_factorybean.other.Fruit;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class App_FactoryBean {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext( App_FactoryBean.class );
        // 要获取工厂bean的话  id名必须是  &id名
        FruitFactoryBean fruitFactoryBean = (FruitFactoryBean) ac.getBean( "&ffb" );
        System.out.println( "工厂本身:"+ fruitFactoryBean + " \t" + fruitFactoryBean.hashCode());

        //如果不加& ，取出的是产品
        Fruit fruit = (Fruit) ac.getBean( "ffb" );
        System.out.println( "产品:"+ fruit + " \t" + fruit.hashCode());

        String[] beanNames = ac.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println( beanName );
        }
    }

    @Bean
    public FruitFactoryBean ffb() {
        return new FruitFactoryBean();
    }

}
