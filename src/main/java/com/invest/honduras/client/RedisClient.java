package com.invest.honduras.client;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import com.invest.honduras.config.ApplicationProperties;
import com.invest.honduras.util.*;

@Component
public class RedisClient {

    @Autowired
    private ApplicationProperties applicationProperties;

	private JedisConnectionFactory jedisFactory;
	private RedisTemplate<String, Object> redisTemplate;
	
	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init() {
		jedisFactory = new JedisConnectionFactory();
		jedisFactory.setHostName(applicationProperties.getRedisHost());
		jedisFactory.setPort(applicationProperties.getRedisPort());
		jedisFactory.afterPropertiesSet();
		redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisFactory);
		redisTemplate.afterPropertiesSet();
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
	}
	
	public String setValueSession(String key, Object value) {
 		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key,Constant.TIME_VALUE_TOKEN, TimeUnit.HOURS);
		return "";
	}
	
	
	public String getSession(String key) {
		Object jsonRedis = redisTemplate.opsForValue().get(key);
		return (String) jsonRedis;
	}
	
	public boolean existSession(String key) {
		Object jsonRedis = redisTemplate.opsForValue().get(key);
		if(jsonRedis != null) {
			return true ;
		}
		return false;
	}
	
}
