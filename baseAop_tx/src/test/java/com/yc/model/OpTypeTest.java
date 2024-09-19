package com.yc.model;

import com.yc.AppDataSourceConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppDataSourceConfig.class})
@Log4j
public class OpTypeTest extends TestCase {

    @Test
    public void test() {
        log.debug(OpType.WITHDRAW.getKey()+" "+OpType.WITHDRAW.getIndex());
    }
}