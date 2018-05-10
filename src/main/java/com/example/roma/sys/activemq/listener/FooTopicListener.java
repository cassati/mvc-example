package com.example.roma.sys.activemq.listener;

import com.example.roma.sys.entity.User;
import org.apache.log4j.Logger;

import javax.jms.*;

public class FooTopicListener implements MessageListener {

    private static Logger log = Logger.getLogger(FooTopicListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                log.debug("Foo receive message: " + text);
            } else if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                User user = (User)objectMessage.getObject();
                log.debug("Foo receive message" + user);
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
