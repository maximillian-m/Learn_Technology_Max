package com.maximillian.logging.RedisCacheWithLettuceFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RedisCacheManager {
    private final RedisTemplate<String, String> redisTemplate;
    private final JsonUtils jsonUtils;

    public <T> void putObjectIntoCache(String key, T object){
        try {
            String jsonValue = jsonUtils.serialize(object);
            redisTemplate.opsForValue().set(key, jsonValue);
        }catch (Exception e){
            System.err.println("Error putting object in redis cache");
            e.printStackTrace();
        }
    }

    public <T> T getObject(String key, Class<T> clazz){
        try {
            String jsonValue = redisTemplate.opsForValue().get(key);
            if (jsonValue != null) {
                return jsonUtils.deserialize(jsonValue, clazz);
            }
        }catch(Exception ex){
            System.out.println("Error fetching object from redis cache");
            ex.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getListObject(String key){
        try{
            String jsonValue = redisTemplate.opsForValue().get(key);
            if (jsonValue != null) {
                return jsonUtils.deserializeList(jsonValue, new TypeReference<List<T>>() {});
            }
        }catch (Exception ex){
            System.out.println("failed to retrieve list from redis cache");
            ex.printStackTrace();
        }
        return null;
    }

    public void evictCacheByKey(String key){
        redisTemplate.delete(key);
    }

}
