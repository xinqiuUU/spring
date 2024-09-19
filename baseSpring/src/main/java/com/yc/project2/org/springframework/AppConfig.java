package com.yc.project2.org.springframework;

import com.yc.ioc.bean2.Apple;
import com.yc.project2.org.springframework.annotation.YcBean;
import com.yc.project2.org.springframework.annotation.YcComponentScan;
import com.yc.project2.org.springframework.annotation.YcConfiguration;
import com.yc.project2.org.springframework.context.YcAnnotationConfigApplicationContext;
import com.yc.project2.org.springframework.test.UserBiz;
import com.yc.project2.org.springframework.test.UserDao;

@YcConfiguration
//@YcComponentScan
//@YcComponentScan(value = {"org.springframework","com.yc.project1"})
@YcComponentScan(basePackages = {"com.yc.project2.org.springframework.test"})
public class AppConfig {

    @YcBean(value = "yc")
    public Apple getApple(){
        return new Apple();
    }
    @YcBean(value = "")
    public Apple snakeapple(){
        return new Apple();
    }


    public static void main(String[] args) {
        YcAnnotationConfigApplicationContext ac = new YcAnnotationConfigApplicationContext(AppConfig.class);
        UserBiz userBiz = (UserBiz) ac.getBean("userBiz");
        userBiz.save();

        UserDao userDao1 = (UserDao) ac.getBean("userDao");
        UserDao userDao2 = (UserDao) ac.getBean("userDao");
        System.out.println(userDao1+"\n"+userDao2);

    }

}
