package com.kodark.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ReportersProcedureDao;
import com.kodark.news.service.ReportersProcedureService;

@Service
public class ReportersProcedureServiceImpl implements ReportersProcedureService {

	@Autowired
	private ReportersProcedureDao reportersProcedureDao;

	@Override
	public List<Map<String, Object>> execuReportersProcedure(Map<String, Object> params) {
		return reportersProcedureDao.execuReportersProcedure(params);
	}
	
	@Override
	public void execuReportersBlindProcedure(Map<String, Object> params) {
		reportersProcedureDao.reportersBlindProcedure(params);
	}

	// 기자프로필
	@Override
	public Map<String, Object> getReporterInfo(Map<String, Object> params) {
		return reportersProcedureDao.getReporterInfo(params);
	}

	@Override
	public List<Map<String, Object>> getPubAndWaitArtlcles(String status) {
		return reportersProcedureDao.getPubAndWaitArtlcles(status);
	}

}
