package com.kodark.news.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ReporterDao;
import com.kodark.news.dao.ReportersProcedureDao;
import com.kodark.news.service.ReportersProcedureService;

@Service	
public class ReportersProcedureServiceImpl implements ReportersProcedureService {
	
	@Autowired
	ReportersProcedureDao reportersProcedureDao;
	
	@Override
	public void execuReportersProcedure(Map<String, Object> params) {
		reportersProcedureDao.reportersBlindProcedure(params);
		
	}

	@Override
	public Map<String, Object> getReporterInfo(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
	
	
}
