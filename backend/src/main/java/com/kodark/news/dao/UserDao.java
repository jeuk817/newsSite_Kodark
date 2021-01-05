package com.kodark.news.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.UserMapper;

@Repository("UserDao")
public class UserDao {
	@Autowired
	private UserMapper userMapper;
	
	public void insertUser(String email, String pwd) {
		userMapper.insertUser(email,pwd);
	}
	public String emailCheck(String email) {
		return userMapper.getEmail(email);
	}
}
