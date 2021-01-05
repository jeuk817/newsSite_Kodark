package com.kodark.news.dao;

import java.util.Map; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ReportersProcedureMapper;

@Repository
public class ReporterDao {
	
	@Autowired
	private ReportersProcedureMapper reportersProcedureMapper;
	
	public Map<String,Object> getReporterInfo(int _id) {
		return reportersProcedureMapper.getReporterInfo(_id);
	}
}
