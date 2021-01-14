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
	public List<Map<String, Object>> execuArticleProcedure(Map<String, Object> params) {
		return articleProcedureDao.excuArticleProcedure(params);
	}

	@Override
	public List<Map<String, Object>> getArticleDetail(Map<String, Object> params) {
		return articleProcedureDao.getArticleDetail(params);
	}

	@Override
	public List<Map<String, Object>> getEmotionInfo(int params) {
		return articleProcedureDao.getEmotionInfo(params);
	}

	@Override
	public List<Map<String, Object>> getCommentReply(int articleId, int commentId) {
		return articleProcedureDao.getCommentReply(articleId, commentId);
	}

}
