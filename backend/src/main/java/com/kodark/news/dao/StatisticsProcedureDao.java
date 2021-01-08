package com.kodark.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.StatiscticsProcedureMapper;

@Repository
public class StatisticsProcedureDao {

	@Autowired
	StatiscticsProcedureMapper statiscticsProcedureMapper;

	public Map<String, Object> excuStatisticsProcedure(Map<String, Object> params) {
		return statiscticsProcedureMapper.statiscticsProcedure(params);
	}

	public List<Map<String, Object>> excuTodayPopularProcedure() {
		return statiscticsProcedureMapper.todayPopularProcedure();
	}

	public List<Map<String, Object>> excuArticleStatisticsProcedure(int articleId) {
		return statiscticsProcedureMapper.articleStatisticsProcedure(articleId);
	}

}
