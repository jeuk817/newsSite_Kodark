package com.kodark.news.service;

import java.util.List;


import com.kodark.news.dto.UserDto;


public interface UserService {
	public void insertUser(String email, String pwd);
	public String emailCheck(String email);
	public List<UserDto> getInfoUsers();
}
