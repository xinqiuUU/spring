package com.yc;

import com.alibaba.druid.pool.DruidDataSource;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@ExtendWith(SpringExtension.class)  //jupiter(junit 5) 整合spring的方案

//导入的是junit4.jar  => 用junit的运行器  SpringJunitClassRunner
//以下两个注解相当于  初始化 spring容器 =>  支持Ioc、di
//junit 已经被spring托管了
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppDataSourceConfig.class})
@Log4j
public class AppDataSourceConfigTest extends TestCase {

    @Autowired
    private DruidDataSource dataSource;

    @Test
    public void druidDataSource() {
        log.debug("测试数据源:"+dataSource);
        Assert.assertNotNull(dataSource);//判断对象是否为空
    }
}