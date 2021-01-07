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
	private ArticleProcedureDao articleProcedureDao;
	
	@Override
	public List<Map<String, Object>> getEmotionInfo(int params) {
		return articleProcedureDao.getEmotionInfo(params);
	}

	@Override
	public List<Map<String, Object>> getCommentReply(int articleId, int commentId) {
		return articleProcedureDao.getCommentReply(articleId, commentId);
	}

}
