package com.example.roma.sys.rabbitmq.listener;

import org.apache.log4j.Logger;

public class Fanout1Listener {

    private static Logger log = Logger.getLogger(Fanout1Listener.class);

    public void listen(String msg) {
        log.debug(msg);
    }
}
