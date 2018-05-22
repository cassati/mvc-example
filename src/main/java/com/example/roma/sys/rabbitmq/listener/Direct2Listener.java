package com.example.roma.sys.rabbitmq.listener;

import org.apache.log4j.Logger;

public class Direct2Listener {

    private static Logger log = Logger.getLogger(Direct2Listener.class);

    public void listen(String msg) {
        log.debug(msg);
    }
}
