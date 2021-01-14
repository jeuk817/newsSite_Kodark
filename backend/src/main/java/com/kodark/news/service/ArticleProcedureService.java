package com.kodark.news.service;

import java.util.List;
import java.util.Map;

public interface ArticleProcedureService {

	public void execuArticleProcedure(Map<String, Object> params);
	
	public List<Map<String, Object>> execuArticleProcedure_2(Map<String, Object> params);

	public List<Map<String, Object>> getArticleDetail(Map<String, Object> params);

	public List<Map<String, Object>> getEmotionInfo(int articleId);

	public List<Map<String, Object>> getCommentReply(int articleId, int commentId);

	public List<Map<String, Object>> execuLatestProcedure(Map<String, Object> params);

	public List<Map<String, Object>> execuCommentProcedure(int i);

}
