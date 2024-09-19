package com.yc.service;

import com.yc.AppDataSourceConfig;
import com.yc.model.Account;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppDataSourceConfig.class})
@Log4j
public class BankBizImplTest extends TestCase {

    @Autowired
    private BankBiz bankBiz;

    @Test
    public void openAccount() { // 开户
        bankBiz.openAccount(1000);
    }

    @Test
    public void deposit() { // 存款
        bankBiz.deposit(1, 1000);
    }

    @Test
    public void withdraw() { // 取款
        bankBiz.withdraw(1, 9);
    }
    @Test
    public void withdraw_Exception() { // 取款异常抛出
        bankBiz.withdraw(55, 100);
    }
    @Test
    public void withdraw_moneyBig() { // 取款金额超过余额
        bankBiz.withdraw(1, 99999);
    }
    @Test
    public void transfer() {
        bankBiz.transfer(1, 9, 2);
    }

    @Test
    public void findAccount() {
        Account a =  bankBiz.findAccount(1);
        log.debug( "查询的账户为:"  +  a );
    }
}