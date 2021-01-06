package com.kodark.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ReportersProcedureMapper;

@Repository
public class ReporterDao {
	
	@Autowired
	ReportersProcedureMapper reportersProcedureMapper;
	 
	public void execuReportersProcedure(Map<String, Object> params) {
		reportersProcedureMapper.reportersProcedure(params);
	}
	
    public Map<String, Object> getReporterInfo(Map<String, Object> params) {
      return reportersProcedureMapper.getReporterInfo(params);
    }

	public List<Map<String, Object>> getPubAndWaitArtlcles(String status) {
		// TODO Auto-generated method stub
		return reportersProcedureMapper.getPubAndWaitArtlcles(status);
	}

}
