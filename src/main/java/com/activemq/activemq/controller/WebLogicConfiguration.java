//package com.activemq.activemq.controller;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.jms.*;
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import java.util.Hashtable;
//
//@Configuration
//@ConditionalOnProperty(name="Boroker", havingValue = "active")
//public class WebLogicConfiguration {
//
//
//    @Bean
//    InitialContext initialContext(@Value("${weblogic.broker-url}") String serverUrl, @Value("${weblogic.jndi-name}") String jndiFactory) {
//        try {
//
//            Hashtable env = new Hashtable();
//            env.put(Context.INITIAL_CONTEXT_FACTORY, jndiFactory);
//            env.put(Context.PROVIDER_URL, serverUrl);
//            InitialContext ic = new InitialContext(env);
//            return ic;
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Bean
//    @Qualifier("weblogicQueue")
//    QueueConnectionFactory queueConnectionFactory(InitialContext initialContext, @Value("${weblogic.connection-factory}") String connectionFactory) {
//        QueueConnectionFactory queueConnectionFactory = null;
//        try {
//            queueConnectionFactory = (QueueConnectionFactory) initialContext.lookup(connectionFactory);
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//
//        return queueConnectionFactory;
//    }
//    @Bean
//    QueueConnection queueConnection(QueueConnectionFactory queueConnectionFactory) throws Exception{
//        return (QueueConnection)queueConnectionFactory.createConnection();
//    }
//
//    @Bean
//    QueueSession queueSession(QueueConnection queueConnection) throws Exception {
//        return (QueueSession)queueConnection.createSession();
//    }
//
//    @Bean
//    QueueSender queueSender(QueueSession queueSession, Queue queue) throws Exception{
//        return queueSession.createSender(queue);
//    }
//
//    @Bean
//    Queue queue(InitialContext initialContext) throws Exception {
//
//        return (Queue) initialContext.lookup("jms/TestJMSQueue");
//    }
//
//
//}
