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
	
	public List<Map<String, Object>> excuStatisticsProcedure(int _id) {
		return statiscticsProcedureMapper.statiscticsProcedure(_id);
	}
}
