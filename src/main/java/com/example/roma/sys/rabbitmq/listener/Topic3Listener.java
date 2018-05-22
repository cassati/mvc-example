package com.example.roma.sys.rabbitmq.listener;

import org.apache.log4j.Logger;

public class Topic3Listener {

    private static Logger log = Logger.getLogger(Topic3Listener.class);

    public void listen(String msg) {
        log.debug(msg);
    }
}
