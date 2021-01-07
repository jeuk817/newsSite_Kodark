package com.kodark.news.service;

import java.util.List;
import java.util.Map;

import com.kodark.news.dto.CategoryDto;

public interface ArticleProcedureService {

	public List<CategoryDto> categoryInfo();

	public List<Map<String, Object>> hotNews();

}