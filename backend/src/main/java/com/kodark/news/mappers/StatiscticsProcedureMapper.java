package com.kodark.news.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface StatiscticsProcedureMapper {
	
	@Select(value = "{CALL statistics_procedure("
			+ "#{_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> statiscticsProcedure(@Param("_id") int _id);
	
}
