package com.kodark.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ReportersProcedureMapper;

@Repository
public class ReportersProcedureDao {

	@Autowired
	ReportersProcedureMapper reportersProcedureMapper;

	public void reportersBlindProcedure(Map<String, Object> params) {
		reportersProcedureMapper.reportersBlindProcedure(params);
	}

	// 기자프로필(이종현)
	public Map<String, Object> getReporterInfo(Map<String, Object> params) {
		return reportersProcedureMapper.getReporterInfo(params);
	}

	public void execuReportersProcedure(Map<String, Object> params) {
		reportersProcedureMapper.reportersProcedure(params);
	}

	// 이푸름
	public List<Map<String, Object>> getPubAndWaitArtlcles(String status) {
		return reportersProcedureMapper.getPubAndWaitArtlcles(status);
	}
	
	public List<Map<String, Object>> reportersArticleList(Map<String, Object> params) {
		return reportersProcedureMapper.reportersArticleList(params);
	}

}
