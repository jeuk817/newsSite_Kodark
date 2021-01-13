package com.kodark.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ArticleProcedureDao;
import com.kodark.news.dto.CategoryDto;
import com.kodark.news.service.ArticleProcedureService;

@Service
public class ArticleProcedureServiceImpl implements ArticleProcedureService {

	@Autowired
	private ArticleProcedureDao articleProcedureDao;

	@Override
	public List<CategoryDto> categoryInfo() {
		List<CategoryDto> category = articleProcedureDao.categoryInfo();
		return category;
	}

	@Override
	public List<Map<String, Object>> hotNews() {
		return articleProcedureDao.hotNews();
	}

	@Override
	public List<Map<String, Object>> execuLatestProcedure(Map<String, Object> params) {
		return articleProcedureDao.latestProcedure(params);
	}

	@Override
	public List<Map<String, Object>> execuCommentProcedure(int i) {

		return articleProcedureDao.commentProcedure(i);
	}

	@Override
	public void execuArticleProcedure(Map<String, Object> params) {
		articleProcedureDao.excuArticleProcedure(params);
	}
	
	@Override
	public Map<String, Object> excuArticleProcedureMap(Map<String, Object> params) {
		return articleProcedureDao.excuArticleProcedureMap(params);
	}
	
	@Override
	public List<Map<String, Object>> excuArticleProcedureList(Map<String, Object> params) {
		return articleProcedureDao.excuArticleProcedureList(params);
	}
	
	@Override
	public List<Map<String, Object>> getArticleDetail(Map<String, Object> params) {
		return articleProcedureDao.getArticleDetail(params);
	}




}
