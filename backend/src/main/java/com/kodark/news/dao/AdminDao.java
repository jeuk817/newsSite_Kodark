package com.kodark.news.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.AdminProcedureMapper;

@Repository
public class AdminDao {
	
	@Autowired
	AdminProcedureMapper adminProcedureMapper;
	 
	public void execuReportersProcedure(Map<String, Object> params) {
		adminProcedureMapper.adminProcedure(params);
	}
}
