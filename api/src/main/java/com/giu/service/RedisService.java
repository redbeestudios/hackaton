package com.giu.service;

/**
 * Created by julian on 29/07/16.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate< String, Object > template;

    public Object getValue(final String key) {
        return template.opsForValue().get(key);
    }

    public void setValue(final String key, final String value) {
        template.opsForValue().set(key, value);
        // set a expire for a message
        template.expire(key, 1, TimeUnit.HOURS);

    }

    public void deleteValue(final String key){
        template.delete(key);
    }

}