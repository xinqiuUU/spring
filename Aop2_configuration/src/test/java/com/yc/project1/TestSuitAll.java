package com.yc.project1;


import com.yc.project1.dao.BankDaoImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//测试套件  一次运行多个测试类
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AppConfigTest.class,
        BankDaoImplTest.class})
public class TestSuitAll {

}
