package com.yc.ioc.bean2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
   因为在某个包中存在一些@Component、@Repository、@Service等注解的类
   spring想托管这些类 ，所以它会扫描这些包， 并把这些类都找出来 并创建这些类的对象  并把这些对象交给spring管理
 */
public class App1_Component {

    public static void main(String[] args) {
        //创建容器
        ApplicationContext ac = new AnnotationConfigApplicationContext( AppConfig.class );
        //从容器中获取bean
        ProductDao pd = (ProductDao) ac.getBean( "productDao" );
        System.out.println( pd);
    }
}
