package com.yc.ioc.bean5_ioc_import;

import com.yc.ioc.bean5_ioc_import.other.WaterMelon;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App2_importSelector {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig_2.class);
        // 获取容器中所有的bean定义
        String[] names = ac.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        WaterMelon wm = (WaterMelon) ac.getBean("com.yc.bean5_ioc_import.other.WaterMelon");
        System.out.println(wm);
    }
}
