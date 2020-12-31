package com.kodark.news.service;

public interface UserService {
	public void insertUser(String email, String pwd);
	public String emailCheck(String email);
}
