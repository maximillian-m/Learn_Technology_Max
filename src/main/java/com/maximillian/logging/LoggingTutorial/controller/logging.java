package com.maximillian.logging.LoggingTutorial.controller;

import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class logging {
   private final Logger log = LogManager.getLogger(this.getClass());
    public void getLoggers(){
        log.info("Here the default logging is info =====>> ");
        Exception e = new RuntimeException("caught an exception");
        log.error("we encountered an exception here =====>>>>");
        log.warn("========>> soon will be deprecated you know =================>>");
    }

    @PostConstruct
    void doPostConstruct(){
        getLoggers();;
    }
}
