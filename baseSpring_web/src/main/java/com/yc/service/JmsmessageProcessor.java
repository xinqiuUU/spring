package com.yc.service;

import com.google.gson.Gson;
import com.yc.model.MessageBean;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class JmsmessageProcessor {

    @Value("${bankMessages}")
    private String queueName;// 队列名称

    @Autowired
    private JmsTemplate jmsTemplate; // 注入 JmsTemplate 实例

    public void sendMessage(MessageBean messageBean) {
        Gson gson = new Gson();
        String json = gson.toJson(    messageBean );
        jmsTemplate.convertAndSend(queueName, json);// 发送消息到队列
        log.info("消息发送成功"+json);
    }
}
