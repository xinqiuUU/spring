package com.yc;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import javax.jms.ConnectionFactory;
@Configuration
@EnableJms //在 Spring 应用程序中启用 JMS（Java Message Service）相关的功能和配置
@PropertySource( value = "classpath:activemq.properties" )
public class JmsActiveMQConfig {

    @Value("${brokerURL}")
    private String brokerURL;
    // 配置连接工厂
    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL( brokerURL );// ActiveMQ 代理地址
        return  connectionFactory;
    }

    // 配置消息监听器容器工厂
    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}

