package com.yc.dao;

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
public class AccountDaoImplTest extends TestCase {

    @Autowired
    private AccountDao accountDao;


    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findCount() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findById() {
        Account a = accountDao.findById(1);
        log.debug( a );
    }

    @Test
    public void addColumn() {
        accountDao.addColumn();
    }
}