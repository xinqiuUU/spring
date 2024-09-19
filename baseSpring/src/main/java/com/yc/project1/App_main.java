package com.yc.project1;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;
import java.sql.SQLException;


public class App_main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext ac  = new AnnotationConfigApplicationContext(AppConfig.class);
        String[] s = ac.getBeanDefinitionNames();
        for (String s1 : s) {
            System.out.println(s1);
        }
        DruidDataSource ds = (DruidDataSource)ac.getBean("druidDataSource");
        Connection conn = ds.getConnection();
        System.out.println(conn);

        BankBiz bankBiz = (BankBiz) ac.getBean("bankBiz");
        bankBiz.showAll();

    }
}
