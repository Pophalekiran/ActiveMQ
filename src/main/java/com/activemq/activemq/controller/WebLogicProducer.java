package com.activemq.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.jms.*;


public class WebLogicProducer implements Producer {

    @Autowired
    QueueSession queueSession;

    @Autowired
    QueueSender queueSender;

    @Autowired
    QueueConnection queueConnection;

    @Autowired
    Queue queue;

    public String sendMessage( String str)  {
        try {
            queueConnection.start();

            queueSender = queueSession.createSender(queue);

            TextMessage textMessage = queueSession.createTextMessage();
            textMessage.setText(str);
            queueSender.send(textMessage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Success";
    }
}
