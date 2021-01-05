package com.kodark.news.mappers;

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
<<<<<<< HEAD
			+ ",#{_pwd, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{result_set, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"			
			+ ")}")
=======
			+ ",#{result_set, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ")}")                   
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
	@Options(statementType = StatementType.CALLABLE)
	public void authProcedure(Map<String, Object> params);
}
