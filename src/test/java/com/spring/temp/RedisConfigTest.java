package com.spring.temp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.spring.temp.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class RedisConfigTest {
	@Autowired
	private RedisTemplate<String, User> redisTemplate2;
	
	@Before
	public void before(){
		// 保存对象
		User user = new User("supermen", 20);
		redisTemplate2.opsForValue().set(user.getUserName(), user);		
		user = new User("蝙蝠侠", 30);
		redisTemplate2.opsForValue().set("蝙蝠侠", user);
		user = new User("蜘蛛侠", 40);
		redisTemplate2.opsForValue().set(user.getUserName(), user);

	}
	
	@Test
	public void Test(){
		Assert.assertEquals(20, redisTemplate2.opsForValue().get("supermen").getAge().longValue());
		Assert.assertEquals(30, redisTemplate2.opsForValue().get("蝙蝠侠").getAge().longValue());
		Assert.assertEquals(40, redisTemplate2.opsForValue().get("蜘蛛侠").getAge().longValue());	
		
		User user = redisTemplate2.opsForValue().get("蝙蝠侠");
		System.out.printf("user:\n" + JSON.toJSONString(user)+"\n");
	}
}
