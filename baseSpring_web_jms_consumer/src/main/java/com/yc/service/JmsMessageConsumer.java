package com.yc.service;

import com.google.gson.Gson;
import com.yc.model.MessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsMessageConsumer {

    @Autowired
    private MailBiz mailBiz;
    @Autowired
    private VelocityTemplateBizImpl velocityTemplateBizImpl;

    @JmsListener(destination = "bankMessages")
    public void receiveMessage(String message) {
        System.out.println("用户接收到的消息:" + message );
        Gson gson = new Gson();
        MessageBean mb = gson.fromJson(message, MessageBean.class);
        //产生要发送的邮件内容
        String content = velocityTemplateBizImpl.genEmailContent(mb.getOpType() ,
              mb.getAccount() , mb.getMoney() ,mb.getToaccountid()  );
        mailBiz.send(  mb.getAccount().getEmail() , "账户变动通知", content   );
    }

}
