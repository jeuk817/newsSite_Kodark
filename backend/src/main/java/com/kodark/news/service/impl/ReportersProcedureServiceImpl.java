package com.kodark.news.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ReporterProcedureDao;
import com.kodark.news.service.ReportersProcedureService;

@Service	
public class ReportersProcedureServiceImpl implements ReportersProcedureService {
	
	@Autowired
	private ReporterProcedureDao reporterProcedureDao;

	@Override
	public void execuReportersProcedure(Map<String, Object> params) {
		reporterProcedureDao.execuReportersProcedure(params);
		
	}
	
}
