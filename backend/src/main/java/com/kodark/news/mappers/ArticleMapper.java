package com.kodark.news.mappers;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.kodark.news.dto.CategoryDto;

@Mapper
public interface ArticleMapper {
	
	@Select("select * from category")
	public  List<CategoryDto> getCategory();
	
}
