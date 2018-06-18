package com.activemq.activemq.controller;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.jndi.JndiTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Properties;
@EnableJms
@Configuration
@ConditionalOnProperty(name="Boroker", havingValue = "noactive")
public class JMSConfiguration {
    @Bean
    public JndiTemplate jndiTemplate() {
        JndiTemplate jndiTemplate = new JndiTemplate();
        Properties jndiProps = new Properties();

        jndiProps.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
        jndiProps.setProperty("java.naming.provider.url", "t3://localhost:7001");
        jndiProps.setProperty("java.naming.security.principal", "weblogic");
        jndiProps.setProperty("java.naming.security.credentials", "welcome1");
        jndiTemplate.setEnvironment(jndiProps);
        return jndiTemplate;
    }

    @Bean
    public UserCredentialsConnectionFactoryAdapter authenticate() throws JMSException, NamingException {
        UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();

        userCredentialsConnectionFactoryAdapter.setUsername("weblogic");
        userCredentialsConnectionFactoryAdapter.setPassword("welcome1");
        userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(jmsConnectionFactory());
        return userCredentialsConnectionFactoryAdapter;
    }

    public ConnectionFactory jmsConnectionFactory() throws NamingException, JMSException {
        ConnectionFactory connectionFactory;
        Context ctx = null;
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://localhost:7001");
        ctx = new InitialContext(env);

        connectionFactory = (javax.jms.ConnectionFactory) ctx.lookup("jms/TestConnectionFactory");
        connectionFactory.createConnection("weblogic", "welcome1");
        return connectionFactory;

    }

    @Bean
    public JndiObjectFactoryBean jmsQueueName() {
        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();

        jndiObjectFactoryBean.setJndiTemplate(jndiTemplate());
        jndiObjectFactoryBean.setJndiName("jms/TestJMSQueue");
        return jndiObjectFactoryBean;
    }

    @Bean(name = "jmsTemplate")
    public JmsTemplate jmsTemplate() throws JMSException, NamingException {
        JmsTemplate jmsTemplate = new JmsTemplate(authenticate());
        jmsTemplate.setSessionTransacted(false);
        jmsTemplate.setReceiveTimeout(5000);
        jmsTemplate.setDefaultDestination((Destination) jmsQueueName().getObject());

        return jmsTemplate;
    }


}
