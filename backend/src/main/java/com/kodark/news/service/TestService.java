package com.kodark.news.service;

import java.util.List;

import java.util.Map;


import com.kodark.news.dto.TestDto;

public interface TestService {
	public List<TestDto> getAllTests();

//	public TestDto getTest(int id);

	public TestDto getTest(int id);
//	public String excuEmpProcedure(String name);
//	public void excuEmpProcedure(TestDto testDto);
	public void excuEmpProcedure(Map<String, Object> parameters);

}
