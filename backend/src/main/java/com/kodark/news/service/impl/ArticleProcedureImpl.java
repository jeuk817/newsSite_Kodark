package com.kodark.news.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ArticleProcedureDao;
import com.kodark.news.service.ArticleProcedureService;
@Service
public class ArticleProcedureImpl implements ArticleProcedureService{
	
	@Autowired
	ArticleProcedureDao articleProcedureDao;
	
	@Override
	public void execuArticleProcedure(Map<String, Object> params) {
		articleProcedureDao.excuArticleProcedure(params);
	}

	@Override
	public Map<String, Object> getArticleDetail(int _articleId) {
		// TODO Auto-generated method stub
		return articleProcedureDao.getArticleDetail(_articleId);
	}

}
