package com.yc.project2.org.springframework.test;

import com.yc.project2.org.springframework.annotation.YcResource;
import com.yc.project2.org.springframework.annotation.YcService;

@YcService
public class UserBiz {

//    @YcAutowired
    @YcResource(value = "userDao")
    private UserDao userDao;

    public void save() {
//        System.out.println("UserBiz的save方法");
        userDao.save();
    }
}
