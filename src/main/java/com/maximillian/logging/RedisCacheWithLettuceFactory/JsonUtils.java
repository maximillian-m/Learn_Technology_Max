package com.maximillian.logging.RedisCacheWithLettuceFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonUtils {
    private static final ObjectMapper obj = new ObjectMapper();

    public <T> T deserialize (String jsonString, Class<T> clazz) throws JsonProcessingException {
        return obj.readValue(jsonString, clazz);
    }

    public <T> String serialize(T object) throws JsonProcessingException {
        return obj.writeValueAsString(object);
    }

    public <T> List<T> deserializeList(String jsonString, TypeReference<List<T>> typeReference) throws JsonProcessingException {
        return obj.readValue(jsonString, typeReference);
    }

    private JsonUtils(){

    }
}
