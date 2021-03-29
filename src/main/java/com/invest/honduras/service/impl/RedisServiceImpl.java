package com.invest.honduras.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invest.honduras.client.RedisClient;
import com.invest.honduras.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {


	@Autowired
	RedisClient redisClient;
	
	@Override
	public boolean existSession(String key) {
		// TODO Auto-generated method stub
		
		return redisClient.existSession(key);
	}

	@Override
	public String setSession(String key, Object value) {
		// TODO Auto-generated method stub
		return redisClient.setValueSession(key, value);
	}

}
