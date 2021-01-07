package com.kodark.news.service;

import java.util.List;
import java.util.Map;

public interface ArticleProcedureService {
	
	public void execuArticleProcedure(Map<String, Object> params);
	public List<Map<String, Object>> getArticleDetail(Map<String, Object> params);
}
