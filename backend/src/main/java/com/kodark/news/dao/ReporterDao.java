package com.kodark.news.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.ReporterMapper;

@Repository
public class ReporterDao {
	
	@Autowired
	private ReporterMapper reporterMapper;
	
	public Map<String,Object> getReporterInfo(int _id) {
		return reporterMapper.getReporterInfo(_id);
	}
}
