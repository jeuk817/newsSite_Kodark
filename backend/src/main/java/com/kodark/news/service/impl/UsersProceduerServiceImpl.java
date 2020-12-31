package com.kodark.news.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.UsersProcedureDao;
import com.kodark.news.service.UsersProceduerService;



@Service
public class UsersProceduerServiceImpl implements UsersProceduerService{

	@Autowired
	UsersProcedureDao usersProcedureDao;
	
	@Override
	public void execuUsersProcedure(Map<String, Object> params) {
		usersProcedureDao.excuUsersProcedure(params);
		
	}
	
	
}
