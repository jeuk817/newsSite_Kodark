package com.kodark.news.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.UserDao;
import com.kodark.news.service.UserService;
@Service
public class UserServiceImpl implements UserService{	
	
	@Autowired
	@Qualifier("UserDao")
	private UserDao userDao;
	
	@Override	
	public int insertUser(String email, String pwd) {
		userDao.insertUser(email, pwd);
		return 1;
		
	}

}
