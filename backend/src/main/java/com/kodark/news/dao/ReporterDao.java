package com.kodark.news.dao;

import java.util.Map; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ReportersProcedureMapper;

@Repository
public class ReporterDao {
	
	@Autowired
<<<<<<< HEAD
	private ReportersProcedureMapper reportersProcedureMapper;
	
	public Map<String,Object> getReporterInfo(int _id) {
		return reportersProcedureMapper.getReporterInfo(_id);
=======
	ReportersProcedureMapper reportersProcedureMapper;
	 
	public void execuReportersProcedure(Map<String, Object> params) {
		reportersProcedureMapper.reportersProcedure(params);
>>>>>>> branch 'backend' of https://github.com/jeuk817/newsSite_Kodark.git
	}

}
