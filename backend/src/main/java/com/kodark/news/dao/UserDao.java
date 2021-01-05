package com.kodark.news.dao;

<<<<<<< HEAD
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
=======
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	
	public Map<String, String>getAuthInfo () {
		return null;
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
	}
}
