package com.kodark.news.service;

import java.util.List;
import java.util.Map;

public interface ArticleProcedureService {
	
	public void execuArticleProcedure(Map<String, Object> params);
	public Map<String, Object> getArticleDetail(int _articleId);
}
