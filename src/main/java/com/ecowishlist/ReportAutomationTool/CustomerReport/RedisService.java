package com.ecowishlist.ReportAutomationTool.CustomerReport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    private final ObjectMapper mapper = new ObjectMapper();

    // Save any object as JSON string
    public void setValue(String key, Object value, Long timeToLiveSeconds) {
        try {
            String json = mapper.writeValueAsString(value);
            if (timeToLiveSeconds != null && timeToLiveSeconds > 0) {
                redisTemplate.opsForValue().set(key, json, timeToLiveSeconds, TimeUnit.SECONDS);
            } else {
                redisTemplate.opsForValue().set(key, json);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize value for Redis", e);
        }
    }

    public <T> T getValue(String key, Class<T> var) throws JsonProcessingException {
        Optional<Object> o = Optional.ofNullable(redisTemplate.opsForValue().get(key));
        ObjectMapper mapper = new ObjectMapper();
        if(o.isPresent())
            return mapper.readValue(o.toString(), var);
        else
            return null;
    }


}
