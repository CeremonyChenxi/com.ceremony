package com.sky.config;

import io.lettuce.core.dynamic.RedisCommandFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class redisConfig {
    @Bean
    public RedisTemplate getRedisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        // value 用 JSON 序列化
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        // hash 的 key 也用 String 序列化
        template.setHashKeySerializer(new StringRedisSerializer());
        // hash 的 value 用 JSON 序列化
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }
}
