package com.activemq.activemq.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ConditionalOnProperty(name="Boroker", havingValue = "active")
public class ActiveMQConfiguration {

    @Bean
    JmsTemplate jmsTemplate(){
        return new JmsTemplate();
    }
}
