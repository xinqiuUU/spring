package com.yc.zy.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener2 {
    @JmsListener(destination = "myQueue")
    public void receiveMessage(String message) {
        System.out.println("用户2接收到的消息: " + message);
    }
}
