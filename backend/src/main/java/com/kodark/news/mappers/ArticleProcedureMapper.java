package com.kodark.news.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;


@Mapper
public interface ArticleProcedureMapper {
	
	@Select(value = "{CALL article_procedure("
			+ "#{_articleId, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"		
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> getEmotionInfo(@Param("_articleId") int _articleId);
	
}
