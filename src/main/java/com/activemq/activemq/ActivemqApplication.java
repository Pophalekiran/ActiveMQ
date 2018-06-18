package com.activemq.activemq;

import com.activemq.activemq.controller.ActiveMQConfiguration;
import com.activemq.activemq.controller.ActiveMQProducer;
import com.activemq.activemq.controller.WebLogicProducer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class ActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }


//    @Bean
//    JmsTemplate jmsTemplate() {
//        JmsTemplate jmsTemplate = new JmsTemplate();
//        //jmsTemplate.setConnectionFactory(connectionFactory);
//        return jmsTemplate;
//    }

    @Bean
    @ConditionalOnProperty(name = "Boroker", havingValue = "active")
    ActiveMQProducer activeMQConfiguration() {
        return new ActiveMQProducer();
    }

    @Bean
    @ConditionalOnProperty(name="Boroker" ,havingValue = "noactive")
    WebLogicProducer webLogicProducer(){
        return new WebLogicProducer();
    }

}