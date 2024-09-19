package com.yc.dao;

import com.yc.AppDataSourceConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppDataSourceConfig.class})
@Log4j
public class AccountDaoimplTest extends TestCase {

    @Autowired
    private AccountDao accountDao;

    @Test
    public void init() {

    }

    @Test
    public void insert() {
        int accountid = accountDao.insert(1000);
        log.info( "插入的账户为:" + accountid);
        Assert.assertNotNull(accountid);
    }

    @Test
    public void update() {
        assertTrue(accountDao.update(3, 10000) > 0);
    }

    @Test
    public void findCount() {
        log.info("账户总数:"+ accountDao.findCount());
    }

    @Test
    public void findAll() {
        log.info(accountDao.findAll());
    }

    @Test
    public void findById() {
        log.info(accountDao.findById(1));
    }
}