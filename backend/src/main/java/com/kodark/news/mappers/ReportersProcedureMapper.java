package com.kodark.news.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface ReportersProcedureMapper {

	@Select(value = "{CALL reporter_procedure("
			+ "#{_switch, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer }"	
			+ ",#{_email, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{result_set, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void reportersProcedure(Map<String, Object> params);
	
	//기자프로필(이종현)
	@Select(value = "{CALL reporter_profile_procedure("
			+ "#{_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public Map<String, Object> getReporterInfo(Map<String, Object> params);

	@Select(value = "{CALL blind_procedure("
			+ "#{_article_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ ",#{_reporter_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"		
			+ ",#{result_set, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"		
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void reportersBlindProcedure(Map<String, Object> params);

}
