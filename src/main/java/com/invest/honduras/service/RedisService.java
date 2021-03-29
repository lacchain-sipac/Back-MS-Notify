package com.invest.honduras.service;

public interface RedisService {


	boolean existSession(String key);
	String  setSession(String key, Object value);
}
