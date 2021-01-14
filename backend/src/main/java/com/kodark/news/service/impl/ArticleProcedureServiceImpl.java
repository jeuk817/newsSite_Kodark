package com.kodark.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ArticleProcedureDao;
import com.kodark.news.service.ArticleProcedureService;

@Service
public class ArticleProcedureServiceImpl implements ArticleProcedureService {

	@Autowired
	private ArticleProcedureDao articleProcedureDao;

	public void execuArticleProcedure(Map<String, Object> params) {
		articleProcedureDao.execuArticleProcedure(params);
	}
	
	@Override
	public List<Map<String, Object>> execuArticleProcedure_2(Map<String, Object> params) {
		return articleProcedureDao.execuArticleProcedure_2(params);
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
