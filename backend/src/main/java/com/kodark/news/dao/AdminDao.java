package com.kodark.news.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.dto.CategoryDto;
import com.kodark.news.mappers.AdminMapper;

@Repository
public class AdminDao {
	
	@Autowired
	private AdminMapper adminMapper;

	public List<CategoryDto> categoryInfo() {		
		return adminMapper.getCategory();
	}
	
}
