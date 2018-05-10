package com.example.roma.sys.activemq.listener;

import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyQueueListener implements MessageListener {

    private static Logger log = Logger.getLogger(MyQueueListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            //取消息内容
            String text = textMessage.getText();
            log.debug("Receive message: " + text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
