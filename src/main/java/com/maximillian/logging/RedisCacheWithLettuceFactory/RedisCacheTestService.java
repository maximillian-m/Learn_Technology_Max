package com.maximillian.logging.RedisCacheWithLettuceFactory;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisCacheTestService {
    private final RedisCacheManager redisCacheManager;

    @PostConstruct
    void doPostConstruct(){
       String key = "IFY_KEY21";
       redisCacheManager.evictCacheByKey(key);
        System.out.println("cache evict successful");
//       String object = "Hello everyone okay okay okay";
//       redisCacheManager.putObjectIntoCache(key, object);

    }


}
