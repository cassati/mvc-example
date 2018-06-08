package com.example.roma.sys.controller;

import com.example.framework.core.springside.SpringContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="rabbitmq/*")
public class RabbitmqController {

    private static Logger log = Logger.getLogger(RabbitmqController.class);

    //@Autowired
    private RabbitTemplate amqpTemplateTopic;
    //@Autowired
    private RabbitTemplate amqpTemplateFanout;
    //@Autowired
    private RabbitTemplate amqpTemplateDirect;

    @ResponseBody
    @RequestMapping(value = "sendDirectQueue1And2.do")
    public String sendDirectQueue1And2(HttpServletRequest request, HttpServletResponse response, Model model,
                                    String name) {
        log.debug("in the sendDirectQueue1And2");

        String ret = "200";

        try {
            amqpTemplateDirect = SpringContextHolder.getBean("amqpTemplateDirect");
            name = StringUtils.isBlank(name) ? "defaultName" : name;
            amqpTemplateDirect.convertAndSend("to_direct_key", name);
        } catch (AmqpException e) {
            e.printStackTrace();
            ret = "500";
        }

        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "sendDirectQueue3.do")
    public String sendDirectQueue3(HttpServletRequest request, HttpServletResponse response, Model model,
                                    String name) {
        log.debug("in the sendDirectQueue3");

        String ret = "200";

        try {
            amqpTemplateDirect = SpringContextHolder.getBean("amqpTemplateDirect");
            name = StringUtils.isBlank(name) ? "defaultName" : name;
            amqpTemplateDirect.convertAndSend("to_direct_key_another", name);
        } catch (AmqpException e) {
            e.printStackTrace();
            ret = "500";
        }

        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "sendTopicQueue1And2.do")
    public String sendTopicQueue1And2(HttpServletRequest request, HttpServletResponse response, Model model,
                                    String name) {
        log.debug("in the sendTopicQueue1And2");

        String ret = "200";

        try {
            amqpTemplateTopic = SpringContextHolder.getBean("amqpTemplateTopic");
            name = StringUtils.isBlank(name) ? "defaultName" : name;
            amqpTemplateTopic.convertAndSend("patt.topic.queue.1or2", name);
        } catch (AmqpException e) {
            e.printStackTrace();
            ret = "500";
        }

        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "sendTopicQueue3.do")
    public String sendTopicQueue3(HttpServletRequest request, HttpServletResponse response, Model model,
                                    String name) {
        log.debug("in the sendTopicQueue3");

        String ret = "200";

        try {
            amqpTemplateTopic = SpringContextHolder.getBean("amqpTemplateTopic");
            name = StringUtils.isBlank(name) ? "defaultName" : name;
            amqpTemplateTopic.convertAndSend("patt.topic.queue3.any", name);
        } catch (AmqpException e) {
            e.printStackTrace();
            ret = "500";
        }

        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "sendFanoutQueue1And2.do")
    public String sendFanoutQueue1And2(HttpServletRequest request, HttpServletResponse response, Model model,
                                    String name) {
        log.debug("in the sendFanoutQueue1And2");

        String ret = "200";

        try {
            amqpTemplateFanout = SpringContextHolder.getBean("amqpTemplateFanout");
            name = StringUtils.isBlank(name) ? "defaultName" : name;
            amqpTemplateFanout.convertAndSend(name);
        } catch (AmqpException e) {
            e.printStackTrace();
            ret = "500";
        }

        return ret;
    }

}
