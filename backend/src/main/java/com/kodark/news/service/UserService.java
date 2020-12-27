package com.kodark.news.service;


import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.AuthStringDao;
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
	@Autowired
	private AuthStringDao authDao;
	
	//signUp
	public void signUp(UserDto dto)throws Exception{
		dao.signUp(dto);
	}
	
	//signIn
	public UserDto signIn(UserDto dto)throws Exception{
		return dao.signIn(dto);
	}
	
	//createAuthKey
	public AuthStringDao updateAuthKey(UserDto dto) throws Exception{
		authDao.updateAuthKey(dto);
		
		return authDao;
	}
	
	//authVerify
	public UserDto updateAuthStatus(UserDto dto) throws Exception {
		return authDao.authVerify(dto);
		
	}
		
}
