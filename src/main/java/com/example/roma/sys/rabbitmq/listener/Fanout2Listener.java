package com.example.roma.sys.rabbitmq.listener;

import org.apache.log4j.Logger;

public class Fanout2Listener {

    private static Logger log = Logger.getLogger(Fanout2Listener.class);

    public void listen(String msg) {
        log.debug(msg);
    }
}
