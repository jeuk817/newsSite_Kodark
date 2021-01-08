package com.kodark.news.service.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ReportersProcedureDao;
import com.kodark.news.service.ReportersProcedureService;

@Service	
public class ReportersProcedureServiceImpl implements ReportersProcedureService {
	
	@Autowired
	private ReportersProcedureDao reporterDao;

	@Override
	public void execuReportersProcedure(Map<String, Object> params) {
		reporterDao.execuReportersProcedure(params);
		
	}
	
	//기자프로필
	@Override
	public Map<String, Object> getReporterInfo(Map<String, Object> params) {		
		return reporterDao.getReporterInfo(params);
	}
	
	
}
