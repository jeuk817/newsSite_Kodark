package com.kodark.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.dto.CategoryDto;
import com.kodark.news.mappers.ArticleProcedureMapper;

@Repository
public class ArticleProcedureDao {
	@Autowired
	ArticleProcedureMapper articleProcedureMapper;

	public void excuArticleProcedure(Map<String, Object> params) {
		articleProcedureMapper.articleProcedure(params);
	}

	public List<Map<String, Object>> getArticleDetail(Map<String, Object> params) {
		return articleProcedureMapper.getArticleDetail(params);
	}

	// 이종현(기사감정표현 가져오기)
	public List<Map<String, Object>> getEmotionInfo(int _articleId) {
		return articleProcedureMapper.getEmotionInfo(_articleId);
	}

	public List<Map<String, Object>> getCommentReply(int _articleId, int _commentId) {
		return articleProcedureMapper.getCommentReply(_articleId, _commentId);
	}

	public List<CategoryDto> categoryInfo() {
		return articleProcedureMapper.getCategory();
	}

	public List<Map<String, Object>> hotNews() {
		return articleProcedureMapper.getHotNews();
	}

	public List<Map<String, Object>> latestProcedure(Map<String, Object> params) {
		return articleProcedureMapper.latestProcedure(params);
	}

	public List<Map<String, Object>> commentProcedure(Map<String, Object> params) {
		return articleProcedureMapper.commentProcedure(params);
	}

}
