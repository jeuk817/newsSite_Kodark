package com.kodark.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.AuthProcedureDao;
import com.kodark.news.service.AuthProcedureService;

@Service
public class AuthProcedureServiceImpl implements AuthProcedureService {

	@Autowired
	AuthProcedureDao authProcedureDao;

	@Override
	public void execuAuthProcedure(Map<String, Object> params) {
		authProcedureDao.excuAuthProcedure(params);
	}

	
}
