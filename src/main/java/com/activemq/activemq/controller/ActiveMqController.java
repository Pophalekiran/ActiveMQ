package com.activemq.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;

@RestController
public class ActiveMqController {

    @Autowired  Producer producer;
  @PostMapping("/hello")
  String push(@RequestBody String str) throws Exception {
      producer.sendMessage(str);
         return "Success";
    }



}
