package com.kodark.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.AdminProcedureDao;
import com.kodark.news.service.AdminProcedureService;

@Service
public class AdminProcedureServiceImpl implements AdminProcedureService {

	@Autowired
	AdminProcedureDao adminProcedureDao;

	@Override
	public void execuAdminProcedure(Map<String, Object> params) {
		adminProcedureDao.excuAdminProcedure(params);
	}
	
	@Override
	public List<Map<String, Object>> execuAdminProcedureList(Map<String, Object> params) {
		return adminProcedureDao.execuAdminProcedureList(params);
	}

	@Override
	public List<Map<String, Object>> getWaitArticles(String _status) {
		return adminProcedureDao.getWaitArticles(_status);
	}

	// ���ڸ��
	@Override
	public List<Map<String, Object>> getReporterList() {
		return adminProcedureDao.getReporterList();
	}
}
