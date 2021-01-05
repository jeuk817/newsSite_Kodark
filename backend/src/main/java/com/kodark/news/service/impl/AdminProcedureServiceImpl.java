package com.kodark.news.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.AdminDao;
import com.kodark.news.service.AdminProcedureService;

@Service
public class AdminProcedureServiceImpl implements AdminProcedureService {
	@Autowired
	private AdminDao adminDao;

	@Override
	public void execuAdminProcedure(Map<String, Object> params) {
		adminDao.execuReportersProcedure(params);
		
	}
}
