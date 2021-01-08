package com.kodark.news.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface AuthProcedureMapper {
	@Select(value = "{CALL auth_procedure("
			+ "#{_switch, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_email, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_auth_string, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_pwd, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{result_set, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void authProcedure(Map<String, Object> params);

	// ���ڸ��
	@Select(value = "{CALL admin_repoters_procedure}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> getInfoReporters();
}

