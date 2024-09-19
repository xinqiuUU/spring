package com.yc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/*
    配置邮件发送器
 */
@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfig {

    @Value("${mail.host}")
    public  String host;
    @Value("${mail.port}")
    public int port;
    @Value("${mail.username}")
    public String username;
    @Value("${mail.password}")
    public String password;

    // 定义一个 JavaMailSender 类型的 Bean，用于发送邮件
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host); // 替换为您的 SMTP 服务器地址
        mailSender.setPort(port); // 替换为您的 SMTP 服务器端口
        mailSender.setUsername(username); // 替换为您的邮箱账号
        mailSender.setPassword(password); // 替换为您的邮箱密码

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        if(port == 587) {
            props.put("mail.smtp.starttls.enable", "true");
        }
        if(port == 465) {
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        }
        //        props.put("mail.debug", "true");

        return mailSender;
    }
}
