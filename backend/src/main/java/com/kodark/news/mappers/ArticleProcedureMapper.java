package com.kodark.news.mappers;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.kodark.news.dto.CategoryDto;

@Mapper
public interface ArticleProcedureMapper {
	
	@Select("select * from category")
	public  List<CategoryDto> getCategory();
	
}
