package com.kodark.news.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kodark.news.dto.CategoryDto;

public interface ArticleService {

	List<CategoryDto> CategoryInfo();

	
}
