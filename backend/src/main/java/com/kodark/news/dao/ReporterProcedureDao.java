package com.kodark.news.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ReporterProcedureMapper;


@Repository
public class ReporterProcedureDao {
	
	@Autowired
	ReporterProcedureMapper reporterProcedureMapper;
	
	public Map<String, Object> getReportInfo(int id) {
		return reporterProcedureMapper.getReportInfo(id);
	}
}
