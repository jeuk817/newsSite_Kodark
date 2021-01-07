package com.kodark.news.service;

import java.util.List;
import java.util.Map;

public interface StatisticsService {
	public Map<String, Object> execuStatisticsProcedure(Map<String, Object> params);
	
	public List<Map<String, Object>> execuTodayPopularProcedure();
	
	public List<Map<String, Object>> execuArticleStatisticsProcedure(int articleId);
	
}
