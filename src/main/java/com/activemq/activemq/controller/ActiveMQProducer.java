package com.activemq.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;


public class ActiveMQProducer implements Producer{

@Autowired
    JmsTemplate jmsTemplate;
    @Override
    public String sendMessage(String str) {
        jmsTemplate.convertAndSend(str);

        return "Success";
    }
}
