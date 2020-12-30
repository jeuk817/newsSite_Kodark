package com.kodark.news.service;

import java.util.List;

import com.kodark.news.dto.TestDto;

public interface TestService {
	public List<TestDto> getAllTests();
//	public TestDto getTest(int id);
	
	public void insertUser();
	public void deleteUser(int id);
	public void empProcedure(String name, int age);
}
