package com.yc.project1;

import com.yc.project1.bean.BankAccount;
import com.yc.project1.service.BankBiz;
import com.yc.project1.service.BankBizImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AppTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext( AppConfig.class );
        BankBiz biz = (BankBiz) ac.getBean( "bankBizImpl" );
        biz.showAll();

        biz.update( new BankAccount( 1 , 99999.00 ));

    }
}
