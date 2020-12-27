package com.kodark.news.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kodark.news.dto.ArticleDto;

@Mapper
public interface ArticleMapper {
	
	@Select("SELECT *FROM ARTICLE WHERE id =${pageid}")
	public ArticleDto getArticleDto(@Param("pageid") int pageid);
}
