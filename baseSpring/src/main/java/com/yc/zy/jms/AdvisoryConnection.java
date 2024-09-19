package com.yc.zy.jms;

import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AdvisoryConnection {

    //监听 ActiveMQ 的连接相关通知
    @JmsListener(destination = "ActiveMQ.Advisory.Connection")
    public void receiveMessage(ActiveMQMessage message) {
        System.out.println("连接信息: " + message);
    }

}
