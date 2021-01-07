package com.kodark.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ArticleProcedureMapper;

@Repository
public class ArticleProcedureDao {
	
	@Autowired
	private ArticleProcedureMapper articleProcedureMapper;
	
	//이종현(기사감정표현 가져오기)
	public List<Map<String, Object>> getEmotionInfo(int _articleId) {
		return articleProcedureMapper.getEmotionInfo(_articleId);
	}
}
