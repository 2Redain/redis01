package com.spring.temp.dome;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.temp.domain.User;


/**
 * 数据库的存储
 * @author smallpanda
 * @date 2017年3月29日下午2:19:53
 */

public interface UserRepository_dome extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User save(User user);

}
