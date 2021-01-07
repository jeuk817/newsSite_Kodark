package com.kodark.news.service;

import java.util.List;
import java.util.Map;

public interface ReportersProcedureService {
	
	public void execuReportersProcedure(Map<String, Object> params);

	public Map<String, Object> getReporterInfo(Map<String, Object> params);

	public List<Map<String, Object>> getPubAndWaitArtlcles(String status);
}
