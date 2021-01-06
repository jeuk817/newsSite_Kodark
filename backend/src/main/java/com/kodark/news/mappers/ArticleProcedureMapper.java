package com.kodark.news.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
@Mapper
public interface ArticleProcedureMapper {
	@Select(value = "{CALL latest_procedure("
			+ "#{section, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{type, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"			
			+ ",#{data, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"			
			+ ",#{result_set, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"			
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public Map<String, Object> latestProcedure(Map<String, Object> params);
}
