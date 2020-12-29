package com.kodark.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.TestDao;
import com.kodark.news.dto.TestDto;
import com.kodark.news.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	@Qualifier("TestDao")
	private TestDao testDao;
	
	@Override
	public List<TestDto> getAllTests() {
		List<TestDto> allTests = testDao.getTests();
		return allTests;
	}

//	@Override
//	public TestDto getTest(int id) {
//		TestDto test = testDao.getTest(id);
//		return test;
//	}

}
