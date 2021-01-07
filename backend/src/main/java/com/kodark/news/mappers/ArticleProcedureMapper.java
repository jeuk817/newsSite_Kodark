package com.kodark.news.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
@Mapper
public interface ArticleProcedureMapper {
	
	
	@Select(value = "{CALL latest_procedure("
			+ "#{category, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"			
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> latestProcedure(Map<String, Object> params);
	

	@Select(value = "{CALL comment_procedure("
			+ "#{_article_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> commentProcedure(int i);
	
	@Select(value = "{CALL article_procedure("
			+ "#{_article_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ "#{_reporter_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"		
			+ "#{result_set, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"		
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public Map<String, Object> articleBlindProcedure(Map<String, Object> params);
	
}
