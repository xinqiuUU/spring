package com.yc.zy.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener {

    @JmsListener(destination = "myQueue") // 监听myQueue消息队列
    public void receiveMessage(String message) {
        System.out.println("用户1接收到的消息: " + message);
    }
}
