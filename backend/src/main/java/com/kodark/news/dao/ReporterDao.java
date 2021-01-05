package com.kodark.news.dao;

import java.util.Map; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ReportersProcedureMapper;

@Repository
public class ReporterDao {
	
	@Autowired

	private ReportersProcedureMapper reportersProcedureMapper;
	
	//기자프로필(이종현 restAPI 61_line)
	public Map<String, String> getReporterInfo(int _id) {
		return reportersProcedureMapper.getReporterInfo(_id);
	}
	 
	public void execuReportersProcedure(Map<String, Object> params) {
		reportersProcedureMapper.reportersProcedure(params);
	}

}
