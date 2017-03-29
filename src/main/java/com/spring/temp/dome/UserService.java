package com.spring.temp.dome;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.temp.domain.User;


@Service
public class UserService {
	@Autowired
	private UserRepository_dome userRepository_dome;
	@Autowired
	private UserRedis userRedis;
	private static final String keyHead = "mysql:get:user:";
	
	public User findById(Long id){
		User user = userRedis.get(keyHead+id);
		if(user == null){
			user = userRepository_dome.findOne(id);
			System.out.printf("缓存为空");
			if(user != null)
				userRedis.add(keyHead+id, 30L, user);
		}
		return user;
	}
	
	@Transactional
	public User create(User user){
		User newUser = userRepository_dome.save(user);
		if(newUser != null)
			userRedis.add(keyHead+newUser.getId(), 30L, newUser);
		return newUser;
	}
	
	@Transactional
	public User createConfig(User user){
		User newUser = userRepository_dome.save(user);
		if(newUser != null)
			userRedis.addConfig(keyHead+newUser.getId(), 30L, newUser);
		return newUser;
	}
	
	@Transactional
	public User update(User user){
		if(user != null){
			userRedis.delete(keyHead+user.getId());
			userRedis.add(keyHead+user.getId(), 30L, user);
		}
		return userRepository_dome.save(user);

	}
	
	@Transactional
	public void delete(Long id){
		userRedis.delete(keyHead+id);
		userRepository_dome.delete(id);
	}
	
}
