package com.spring.temp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@SuppressWarnings("serial")
@Entity
public class User implements Serializable{

	@Id
	@GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Integer age;

    public User(){}

    public User(Long id,String userName, Integer age){
    	this.id = id;
    	this.userName = userName;
    	this.age = age;
    }
    
    public User(String userName, Integer age) {
        this.userName = userName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

 

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
