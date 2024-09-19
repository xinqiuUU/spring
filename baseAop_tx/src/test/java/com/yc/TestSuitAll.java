package com.yc;


import com.yc.model.OpTypeTest;
import com.yc.dao.AccountDaoimplTest;
import com.yc.service.BankBizImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//测试套件  一次运行多个测试类
@RunWith(Suite.class)
@Suite.SuiteClasses({
        AppDataSourceConfigTest.class  ,
        BankBizImplTest.class ,
        OpTypeTest.class,
        AccountDaoimplTest.class
})
public class TestSuitAll {

}
