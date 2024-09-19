package com.yc.project1.dao;

import com.yc.project1.AppConfig;
import com.yc.project1.bean.BankAccount;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
//@PropertySource("classpath:db.properties")
public class BankDaoImplTest extends TestCase {

    @Autowired
    private BankDao bankDao;

    @Autowired
    private Environment env;

    @Test
    public void testEnv() {

        System.out.println(env);
        Assert.assertEquals( "C:\\Users\\DELL", env.getProperty("user.home") );
        System.out.println(env.getProperty("uname"));
        System.out.println(env.getProperty("pwd"));
    }

    @Test
    public void findAll() {
        List<BankAccount> list = bankDao.findAll();
        Assert.assertNotNull(list);// 断言 对象不为空
        Assert.assertEquals(2, list.size());// 断言 集合大小为5
    }

    @Test
    public void findById() {
        BankAccount bankAccount = bankDao.findById(1);
        Assert.assertNotNull(bankAccount);// 断言 对象不为空
    }

    @Test
    public void update() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(1);
        bankAccount.setBalance(6666.00);
        bankDao.update(bankAccount);
    }
}