package com.kodark.news.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.UserDao;
import com.kodark.news.dto.UserDto;

/**
 * User서비스
 * @author ys
 * 2020-12-24
 */
@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	
	//signUp
	public void signUp(UserDto dto)throws Exception{
		dao.signUp(dto);
	}
	//signUp
	public UserDto signIn(UserDto dto)throws Exception{
		return dao.signIn(dto);
	}
		
}
