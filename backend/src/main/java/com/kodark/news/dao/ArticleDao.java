package com.kodark.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.dto.CategoryDto;
import com.kodark.news.mappers.ArticleMapper;


@Repository
public class ArticleDao {

	@Autowired
	private ArticleMapper articleMapper;

	public List<CategoryDto> categoryInfo() {		
		return articleMapper.getCategory();
	}	

	
}
