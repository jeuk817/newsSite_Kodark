package com.kodark.news.service;

import java.util.Map;

public interface ReportersProcedureService {
	
	public Map<String, Object> getReporterInfo(int id);	
	
	public void execuReportersProcedure(Map<String, Object> params);	
	
}
