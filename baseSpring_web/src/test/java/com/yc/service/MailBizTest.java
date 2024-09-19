package com.yc.service;

import com.yc.AppDataSourceConfig;
import com.yc.EmailConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppDataSourceConfig.class , EmailConfig.class})
@Log4j
public class MailBizTest extends TestCase {

    @Autowired
    private MailBiz mailBiz;

    @Test
    public void send(){
        mailBiz.sendMail("2921310632@qq.com","测试","测试");
    }

}