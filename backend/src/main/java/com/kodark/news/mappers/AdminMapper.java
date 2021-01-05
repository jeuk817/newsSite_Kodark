package com.kodark.news.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface AdminMapper {
	
	//기자목록
	@Select(value = "{CALL admin_repoters_procedure}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String,Object>> getInfoReporters();
}
