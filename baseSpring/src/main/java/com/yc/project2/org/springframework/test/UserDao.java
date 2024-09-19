package com.yc.project2.org.springframework.test;

import com.yc.project2.org.springframework.annotation.YcLazy;
import com.yc.project2.org.springframework.annotation.YcRepository;
import com.yc.project2.org.springframework.annotation.YcScope;
import org.springframework.stereotype.Repository;

@YcRepository
//@YcLazy
@YcScope("prototype")
public class UserDao {
    public void save(){
        System.out.println("UserDao的save方法");
    }
}
