package com.yc.zy.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@ComponentScan  // 扫描组件
@Configuration
@PropertySource("classpath:email.properties")
public class MailConfig {

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
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}