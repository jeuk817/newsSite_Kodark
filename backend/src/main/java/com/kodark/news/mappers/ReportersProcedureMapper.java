package com.kodark.news.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
<<<<<<< HEAD
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface ReportersProcedureMapper {
	
	@Select(value = "{CALL reporter_procedure("
			+ "#{_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public Map<String,Object> getReporterInfo(@Param("_id") int _id);
=======
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
>>>>>>> branch 'backend' of https://github.com/jeuk817/newsSite_Kodark.git
}
