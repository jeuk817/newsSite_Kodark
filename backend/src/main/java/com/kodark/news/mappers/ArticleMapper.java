package com.kodark.news.mappers;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.kodark.news.dto.CategoryDto;

@Mapper
public interface ArticleMapper {
	
	@Select(value = "{CALL article_procedure}")
	@Options(statementType = StatementType.CALLABLE)
	public  List<CategoryDto> getCategory();
	

	
}
