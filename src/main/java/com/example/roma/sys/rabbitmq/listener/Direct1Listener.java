package com.example.roma.sys.rabbitmq.listener;

import org.apache.log4j.Logger;

public class Direct1Listener {

    private static Logger log = Logger.getLogger(Direct1Listener.class);

    public void listen(String msg) {
        log.debug(msg);
    }
}
