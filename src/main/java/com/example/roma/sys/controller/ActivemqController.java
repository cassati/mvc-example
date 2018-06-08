package com.example.roma.sys.controller;

import com.example.framework.core.springside.SpringContextHolder;
import com.example.roma.sys.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="activemq/*")
public class ActivemqController {

    private static Logger log = Logger.getLogger(ActivemqController.class);

    //@Autowired
    private JmsTemplate jmsTemplate;
    //@Autowired
    private Destination topicDestination;
    //@Autowired
    private Destination queueDestination;

    @ResponseBody
    @RequestMapping(value = "sendTopic.do")
    public User sendTopic(HttpServletRequest request, HttpServletResponse response, Model model,
                                    String name, String username) {
        log.debug("in the sendTopic");

        jmsTemplate = SpringContextHolder.getBean("jmsTemplate");
        topicDestination = SpringContextHolder.getBean("topicDestination");

        final User user = new User();
        user.setName(StringUtils.isBlank(name) ? "defaultName" : name);
        user.setUsername(StringUtils.isBlank(username) ? "defaultUserName" : username);

        jmsTemplate.send(topicDestination, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage(user);
                return objectMessage;
            }
        });

        return user;
    }

    @ResponseBody
    @RequestMapping(value = "sendQueue.do")
    public String sendQueue(HttpServletRequest request, HttpServletResponse response, Model model,
                                    String message) {
        log.debug("in the sendQueue");

        jmsTemplate = SpringContextHolder.getBean("jmsTemplate");
        queueDestination = SpringContextHolder.getBean("queueDestination");

        final String msg = StringUtils.isBlank(message) ? "defaultMessage" : message;
        jmsTemplate.send(queueDestination, new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage(msg);
                return textMessage;
            }
        });

        return msg;
    }

}
