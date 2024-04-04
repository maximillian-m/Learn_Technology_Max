package com.maximillian.logging.RedisCacheWithLettuceFactory;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RedisCacheTestService {
    private final RedisCacheManager redisCacheManager;

    @PostConstruct
    void doPostConstruct(){
       String key = "IFY_KEY21";
//       redisCacheManager.evictCacheByKey(key);
//        System.out.println("cache evict successful");
//       String object = "Hello everyone okay okay okay";
//       redisCacheManager.putObjectIntoCache(key, Collections.singleton("Hello every body"));
//        System.out.println("done putting object in cache");
        List<String> list = redisCacheManager.getListObject(key);
        System.out.println("list retrieved ===>" + list.get(0));
    }


}
