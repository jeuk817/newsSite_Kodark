package com.kodark.news.dao;

import java.util.List;

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
	
//	public TestDto getTest(int id) {
//		return testMapper.getEmp(id);
//	}
}
