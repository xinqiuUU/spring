package com.yc.project1;

import com.alibaba.druid.pool.DruidDataSource;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

//以下两个注解相当于  初始化 spring容器 =>  支持Ioc、di
//junit 已经被spring托管了
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@Log4j
public class AppConfigTest extends TestCase {

    @Autowired
    private DruidDataSource dataSource;

    @Test
    public void druidDataSource() {
        log.info("我是lombok生成的日志对象.....");
        assertNotNull(dataSource);//判断对象是否为空
    }
}