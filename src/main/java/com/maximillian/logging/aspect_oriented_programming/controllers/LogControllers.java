package com.maximillian.logging.aspect_oriented_programming.controllers;

import com.maximillian.logging.aspect_oriented_programming.services.LogServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/endpoint")
public class LogControllers {

    private final LogServices logServices;

    public LogControllers(LogServices logServices) {
        this.logServices = logServices;
    }

    @GetMapping("/throws")
    public String endPointForThrows() throws Exception {
       return logServices.practiceLogging("Max");
    }
    @GetMapping("/returning")
    public String endPointForReturns() throws Exception {
       return logServices.practiceLoggingForReturning("Max");
    }

}
