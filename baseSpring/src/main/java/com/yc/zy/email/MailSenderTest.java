package com.yc.zy.email;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MailSenderTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(MailConfig.class);
        String[] s = ac.getBeanDefinitionNames();
        for (String s1 : s) {
            System.out.println(s1);
        }
        MailService mailService = ac.getBean( MailService.class );

        String to = "3108542443@qq.com";
        String subject = "Hello from Spring Email";
        String text = "这是一个Spring Email测试邮件";


        mailService.sendMail(to, subject, text);
    }
}