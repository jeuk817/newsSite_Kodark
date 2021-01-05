package com.kodark.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ArticleDao;
import com.kodark.news.dto.CategoryDto;
import com.kodark.news.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired	
	private ArticleDao articleDao;

	@Override
	public List<CategoryDto>CategoryInfo() {
		
		List<CategoryDto> category = articleDao.categoryInfo();
		
		return category;
	}
	
}
