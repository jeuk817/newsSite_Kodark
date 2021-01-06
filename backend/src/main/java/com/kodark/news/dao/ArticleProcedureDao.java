package com.kodark.news.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.dto.CategoryDto;
import com.kodark.news.mappers.ArticleProcedureMapper;

@Repository
public class ArticleProcedureDao {
	
	@Autowired
	private ArticleProcedureMapper articleProcedureMapper;

	public List<CategoryDto> categoryInfo() {		
		return articleProcedureMapper.getCategory();
	}
}
