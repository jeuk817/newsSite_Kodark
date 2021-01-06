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
	

	@Select(value = "{CALL comment_procedure()}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> commentProcedure();
	
}
