package com.yc.zy.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
@Component
public class MessageProducer {

    @Autowired
    private JmsTemplate jmsTemplate; // 注入 JmsTemplate 实例

    public void sendMessage(String message) {
        jmsTemplate.convertAndSend("myQueue", message);// 发送消息到队列
        System.out.println("发送的消息为: " + message);
    }

}
