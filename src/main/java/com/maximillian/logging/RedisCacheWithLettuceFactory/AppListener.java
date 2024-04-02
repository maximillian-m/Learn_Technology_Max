package com.maximillian.logging.RedisCacheWithLettuceFactory;

import jakarta.annotation.Nonnull;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class AppListener implements ApplicationListener<ContextRefreshedEvent> {
    private final StringRedisTemplate stringRedisTemplate;

    public AppListener(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void onApplicationEvent(@Nonnull ContextRefreshedEvent event) {
        try{
            stringRedisTemplate.opsForValue().get("nonexistentKeyJustForConnectionCheck");
            System.out.println("Connection Successful");
        }catch (Exception ex){
            System.err.println("connection unsuccessful");
        }
    }
}
