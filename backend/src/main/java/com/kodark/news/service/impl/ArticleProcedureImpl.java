package com.kodark.news.service.impl;

import java.util.List;
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
	public List<Map<String, Object>> getArticleDetail(Map<String, Object> params) {
		return articleProcedureDao.getArticleDetail(params);
	}

}
