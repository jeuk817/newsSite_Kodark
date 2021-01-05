package com.kodark.news.service;

import java.util.Map;

public interface ReportersProcedureService {
	
	public void execuReportersProcedure(Map<String, Object> params);
	
	//기자프로필(이종현 restAPI 61_line)
	public Map<String, String> getReporterInfo(int id);
}
