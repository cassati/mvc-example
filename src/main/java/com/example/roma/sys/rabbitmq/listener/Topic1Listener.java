package com.example.roma.sys.rabbitmq.listener;

import org.apache.log4j.Logger;

public class Topic1Listener {

    private static Logger log = Logger.getLogger(Topic1Listener.class);

    public void listen(String msg) {
        log.debug(msg);
    }
}
