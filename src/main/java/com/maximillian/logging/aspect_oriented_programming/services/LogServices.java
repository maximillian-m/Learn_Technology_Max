package com.maximillian.logging.aspect_oriented_programming.services;

import com.maximillian.logging.annotation.Log;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class LogServices {


    public void helloPrint(){
      String hello = """
              Hello Chukwuebuka, how are you doing today, I hope you are good......
              I am writing to tell you that I will be leaving Alabama today or tomorrow to Nigeria.
              Also, I want to let you know that I had a wonderful time with your family, and I pray the blessings of the Lord stays with you always.
              ciao.
              """;
        System.out.println(hello);
    }
    public void helloPrintArgs(String name){
      String hello = """
              Hello %s, how are you doing today, I hope you are good......
              I am writing to tell you that I will be leaving Alabama today or tomorrow to Nigeria.
              Also, I want to let you know that I had a wonderful time with your family, and I pray the blessings of the Lord stays with you always.
              ciao.
              """.formatted(name);
        System.out.println(hello);
    }

    public String practiceLogging(String name) throws Exception {
        String hello = "Hello world";
        throw new Exception("I am throwing an exception");
    }
    @Log(printParamsValues = true)
    public String practiceLoggingForReturning(String name) throws Exception {
        return "Hello world " + name ;
    }
}
