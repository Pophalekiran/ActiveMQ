package com.activemq.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@RestController
public class ActiveMqController {
    @Autowired
    JmsTemplate jmsTemplate;

    @PostMapping("/hello")

    String push(@RequestBody String str) {

        jmsTemplate.convertAndSend("outbound.demo", str);
        return "Success";
    }

    @GetMapping("/read")
    String readMessage(){

        Message str= jmsTemplate.receive("outbound.demo");
        try {
            if(str instanceof TextMessage){
                TextMessage textMessage = (TextMessage)str;
                return textMessage.getText();
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }

        return "dd";
    }

//    @JmsListener(destination = "outbound.demo")
//    String readMessage(final Message message) {
//        String readmessage=null;
//        if (message instanceof TextMessage) {
//            try {
//                readmessage=((TextMessage) message).getText().toString();
//                System.out.println(readmessage);
//            } catch (JMSException e) {
//                e.printStackTrace();
//            }
//        }
//        return readmessage;
//    }
}
