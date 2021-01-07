package com.kodark.news.service;

import java.util.List;
import java.util.Map;

public interface ArticleProcedureService {
	public List<Map<String, Object>> getEmotionInfo(int articleId);
	public List<Map<String, Object>> getCommentReply(int articleId,int commentId);
}
