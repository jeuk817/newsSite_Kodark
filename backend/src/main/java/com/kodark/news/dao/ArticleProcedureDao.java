package com.kodark.news.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ArticleProcedureMapper;

@Repository
public class ArticleProcedureDao {
	@Autowired
	ArticleProcedureMapper articleProcedureMapper;
	
	public void excuArticleProcedure(Map<String, Object> params) {
		articleProcedureMapper.articleProcedure(params);
	}
	
	public Map<String, Object> getArticleDetail(int _articleId) {
		return articleProcedureMapper.getArticleDetail(_articleId);
	}
}
