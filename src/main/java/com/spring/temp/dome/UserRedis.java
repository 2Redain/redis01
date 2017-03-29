package com.spring.temp.dome;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.spring.temp.domain.User;

@Repository
public class UserRedis {
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	@Autowired
	private RedisTemplate<String,User> redisTemplate2;
	/*
	 * 添加相关信息进入缓存
	 */
	public void add(String key,Long time,User user){
		redisTemplate.opsForValue().set(key,JSON.toJSONString(user), time, TimeUnit.MINUTES);
	}
	public void add(String key,Long time,List<User> users){
		redisTemplate.opsForValue().set(key, JSON.toJSONString(users), time, TimeUnit.MINUTES);
	}
	public void addConfig(String key,Long time,User user){
		redisTemplate2.opsForValue().set(key, user, time, TimeUnit.MINUTES);
	}
	/**
	 * 只从缓存中获取的用户信息
	 * @param key
	 * @return
	 */
	public User get(String key){
		User user = null;
		String userJson = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(userJson)){
			user = JSON.parseObject(userJson,User.class);
		}
		return user;
	}
	/**
	 * 只从缓存中获取的用户信息
	 * @param key
	 * @return
	 */
	public List<User> getList(String key){
		List<User> users = null;
		String listuserJson = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(listuserJson)){
			users = JSON.parseObject(listuserJson,new TypeReference<List<User>>(){});
		}
		return users;
	}
	public void delete(String key){
		redisTemplate.opsForValue().getOperations().delete(key);
	}
	
	public String getList1(String key){
		String list = redisTemplate.opsForValue().get(key);
		if(!StringUtils.isEmpty(list)){
			
		}
		return list;
	}
}
