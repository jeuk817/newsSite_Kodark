package com.kodark.news.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface ReporterMapper {
	
	@Select(value = "{CALL reporter_procedure("
			+ "#{_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public Map<String,Object> getReporterInfo(@Param("_id") int _id);
}
