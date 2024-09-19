package com.yc.zy.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@PropertySource("classpath:email.properties")
@Service
public class MailService {

    @Autowired  // 注入JavaMailSender
    private JavaMailSender javaMailSender;

    @Value("${mail.username}")
    public String username;
    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);// 发件人
        message.setTo(to);// 收件人
        message.setSubject(subject);// 邮件主题
        message.setText(text);// 邮件内容
        javaMailSender.send(message);
    }
}