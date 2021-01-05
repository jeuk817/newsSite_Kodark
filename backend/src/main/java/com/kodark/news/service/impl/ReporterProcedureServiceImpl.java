package com.kodark.news.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ReporterProcedureDao;
import com.kodark.news.service.ReporterProcedureService;

@Service
public class ReporterProcedureServiceImpl implements ReporterProcedureService{
	
	@Autowired
	ReporterProcedureDao reporterProcedureDao;


	@Override
	public Map<String, Object> getReporterInfo(int id) {
		return reporterProcedureDao.getReportInfo(id);
	}
	
	
}
