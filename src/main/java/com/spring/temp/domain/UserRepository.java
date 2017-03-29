package com.spring.temp.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * @CacheConfig：主要用于配置该类中会用到的一些共用的缓存配置。
 * 在这里@CacheConfig(cacheNames = "users")：配置了该数据访问对象中返回的内容将存储于名为users的缓存对象中，
 * 我们也可以不使用该注解，直接通过@Cacheable自己配置缓存集的名字来定义
 */
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * @Cacheable：配置了findByName函数的返回值将被加入缓存。
	 * 同时在查询时，会先从缓存中获取，若不存在才再发起对数据库的访问。
	 * #p0（SPEL表达式）表示函数的第一个参数
	 */
    @Cacheable(key = "#p0")
    User findByUserName(String userName);
    
    /**
     * @CachePut：配置于函数上，能够根据参数定义条件来进行缓存，它与@Cacheable不同的是，
     * 它每次都会真是调用函数，所以主要用于数据新增和修改操作上。
     */
	@CachePut(key = "#p0.userName")
    User save(User user);

}
