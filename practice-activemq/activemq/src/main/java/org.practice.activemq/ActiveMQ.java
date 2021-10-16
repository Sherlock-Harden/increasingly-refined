package org.practice.activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author yuansj[yuansj@neusoft.com]
 * @since 2021/10/15 10:25
 **/
public class ActiveMQ {


  public static void main(String[] args){

    new Thread(() -> consumer()).start();

    new Thread(() -> producer()).start();

  }

  private static void consumer() {
    try {
      //1.获取链接工厂
      ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
          "yuansj",
          "Meiyou@123!",
          "tcp://101.37.70.202:61616"
      );
      //2.获取链接
      Connection connection = connectionFactory.createConnection();
      //3.获取session
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      //4.找到目的地，获取destination，消费端，也会从这个目的地获取消息
      Destination queue = session.createQueue("queue");
      //5.创建消息消费者
      MessageConsumer consumer = session.createConsumer(queue);
      connection.start();
      //6.消费消息
      while (true) {
        Message message = consumer.receive();
        System.out.println("message: " + message.toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void producer() {
    try {
      //1.获取链接工厂
      ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
          "yuansj",
          "Meiyou@123!",
          "tcp://101.37.70.202:61616"
      );
      //2.获取链接
      Connection connection = connectionFactory.createConnection();
      //3.获取session
      Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      //4.找到目的地，获取destination，消费端，也会从这个目的地获取消息
      Destination queue = session.createQueue("queue");
      //5.创建消息生产者
      MessageProducer producer = session.createProducer(queue);
      for (int i = 0; i < 100; i++) {
        //6.创建消息
        Message textMessage = session.createTextMessage("hello" + i);
        //7.向目的地写入消息
        producer.send(textMessage);
      }
      //8.关闭链接
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
