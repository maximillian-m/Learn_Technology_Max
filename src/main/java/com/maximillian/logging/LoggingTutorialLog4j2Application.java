package com.maximillian.logging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class LoggingTutorialLog4j2Application {

    public static void main(String[] args) {
        SpringApplication.run(LoggingTutorialLog4j2Application.class, args);
    }

}
