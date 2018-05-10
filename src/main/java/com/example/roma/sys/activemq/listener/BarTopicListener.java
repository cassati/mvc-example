package com.example.roma.sys.activemq.listener;

import com.example.roma.sys.entity.User;
import org.apache.log4j.Logger;

import javax.jms.*;

public class BarTopicListener implements MessageListener {

    private static Logger log = Logger.getLogger(BarTopicListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                log.debug("Bar receive message: " + text);
            } else if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;
                User user = (User)objectMessage.getObject();
                log.debug("Bar receive message" + user);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
