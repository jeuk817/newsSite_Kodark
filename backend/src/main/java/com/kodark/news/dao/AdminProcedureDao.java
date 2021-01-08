package com.kodark.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.AdminProcedureMapper;

@Repository
public class AdminProcedureDao {

	@Autowired
	AdminProcedureMapper adminProcedureMapper;
	
	public void excuAdminProcedure(Map<String, Object> params) {
		adminProcedureMapper.adminProcedure(params);
	}
	
	public List<Map<String, Object>> getWaitArticles() {
		return adminProcedureMapper.getWaitArticle();
	}
	
	//기자목록(rest_API 57_line)
	public List<Map<String, Object>> getReporterList() {
		return adminProcedureMapper.getReporterList();
	}
	
}
