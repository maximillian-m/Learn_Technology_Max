package com.maximillian.logging.aspect_oriented_programming.services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CallerServices {
    private final LogServices logServices;

    public CallerServices(LogServices logServices) {
        this.logServices = logServices;
    }

    @PostConstruct
    void doPostConstruct() throws Exception {
//        logServices.practiceLogging("Chika");
//        logServices.helloPrint();
//        logServices.helloPrintArgs("Patrick");
    }
}
