package com.kodark.news.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ArticleProcedureMapper;
@Repository
public class ArticleProcedureDao {
	@Autowired
	ArticleProcedureMapper articleProcedureMapper;
	
	public Map<String, Object> latestProcedure(Map<String, Object>params){
		return articleProcedureMapper.latestProcedure(params);
	}
}
