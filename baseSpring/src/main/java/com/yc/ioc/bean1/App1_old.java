package com.yc.ioc.bean1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App1_old {

    public static void main(String[] args) {
        Address address = new Address("123 Main St", "Anytown", "CA");

        Student student = new Student(1, "John", address);
        System.out.println(student);

        // 从配置文件中获取bean  资源路径
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Address address1 = (Address) context.getBean("addr");
        System.out.println(address1);

        // 绝对路径
//        ApplicationContext context2 = new FileSystemXmlApplicationContext("C:\\beans.xml");
//        Address address2 = (Address) context.getBean("addr");
//        System.out.println(address2);

    }
}
