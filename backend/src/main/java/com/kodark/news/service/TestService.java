package com.kodark.news.service;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b

import com.kodark.news.dto.TestDto;

public interface TestService {
	public List<TestDto> getAllTests();
<<<<<<< HEAD
//	public TestDto getTest(int id);
=======
	public TestDto getTest(int id);
//	public String excuEmpProcedure(String name);
//	public void excuEmpProcedure(TestDto testDto);
	public void excuEmpProcedure(Map<String, Object> parameters);
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
}
