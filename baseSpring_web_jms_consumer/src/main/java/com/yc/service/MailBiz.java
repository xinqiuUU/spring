package com.yc.service;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@PropertySource("classpath:email.properties")
@Log4j
public class MailBiz {

    @Autowired  // 注入JavaMailSender
    private JavaMailSender MailSender;

    @Value("${mail.username}")
    public String username;

    public void send(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage(); //不包括附件
        MimeMessage mm = MailSender.createMimeMessage();  //可以包括附件
        try{
            // 邮件内容                                        true 表示可以加附件
            MimeMessageHelper message = new MimeMessageHelper(mm, true,"UTF-8");
            message.setFrom(username);// 发件人
            message.setTo(to);// 收件人
            message.setSubject(subject);// 邮件主题
            message.setText(text , true);// 邮件内容 一定要有 true 代表当成html代码
            MailSender.send( mm );
        }catch (Exception e){
            e.printStackTrace();
            log.error("邮件发送失败:"+e.getMessage());
        }

    }

}
