package com.barrett.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory; 
import javax.jms.Destination; 
import javax.jms.JMSException; 
import javax.jms.MessageConsumer; 
import javax.jms.Session; 
  
import org.apache.activemq.ActiveMQConnectionFactory; 
  
/** 
 * 消息消费者（点对点） 
 * 推荐这种的监听方式来消费 
 * @author Administrator 
 */ 
public class JMSConsumer2 { 
  
  
    public static void main(String[] args) { 
        ConnectionFactory connectionFactory; // 连接工厂 
        Connection connection = null; // 连接 
        Session session; // 会话 接受或者发送消息的线程 
        Destination destination; // 消息的目的地 
        MessageConsumer messageConsumer; // 消息的消费者 
  
        // 实例化连接工厂 
        connectionFactory = new ActiveMQConnectionFactory(JMSProducer.USERNAME, JMSProducer.PASSWORD, 
                JMSProducer.BROKEURL); 
  
        try { 
            connection = connectionFactory.createConnection(); // 通过连接工厂获取连接 
            connection.start(); // 启动连接 
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session 
            destination = session.createQueue(JMSProducer.MQ_NAME); // 创建连接的消息队列 
            messageConsumer = session.createConsumer(destination); // 创建消息消费者 
            messageConsumer.setMessageListener(new Listener()); // 注册消息监听 
        } catch (JMSException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace(); 
        } 
    } 
}