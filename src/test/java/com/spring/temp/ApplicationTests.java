package com.spring.temp;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.temp.domain.User;
import com.spring.temp.domain.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private CacheManager cacheManager;

	@Before
	public void before() {
		userRepository.save(new User("AAA", 10));
	}

	@Test
	public void test() throws Exception {
		User u1 = userRepository.findByUserName("AAA");
		System.out.println("第一次查询：" + u1.getAge());

		User u2 = userRepository.findByUserName("AAA");
		System.out.println("第二次查询：" + u2.getAge());

		u1.setAge(20);
		userRepository.save(u1);
		User u3 = userRepository.findByUserName("AAA");
		System.out.println("第三次查询：" + u3.getAge());

	}

}
