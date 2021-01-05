package com.kodark.news.dao;

import java.util.List;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.kodark.news.dto.TestDto;

import com.kodark.news.mappers.TestMapper;

@Repository("TestDao")
public class TestDao {

	@Autowired
//	@Qualifier("TestMapper")
	private TestMapper testMapper;
	
	public List<TestDto> getTests() {
		return testMapper.getEmps();
	}
	
	public TestDto getTest(int id) {
		return testMapper.getEmp(id);
	}
	
	public void excuEmpProcedure(Map<String, Object> parameters) {
		testMapper.empProcedure(parameters);
	}
//	public void excuEmpProcedure(TestDto testDto) {
//		testMapper.empProcedure(testDto);
//	}
//	public String excuEmpProcedure(String name) {
//		return testMapper.empProcedure(name);

//	}
}
