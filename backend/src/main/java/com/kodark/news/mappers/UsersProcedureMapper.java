package com.kodark.news.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface UsersProcedureMapper {	
	@Select(value = "{CALL users_procedure("
			+ "#{_switch, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer }"			
			+ ",#{_email, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_pwd, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_nickName, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_name, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_local, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_birth, mode=INOUT, jdbcType=DATE, javaType=java.util.Date}"
			+ ",#{_gender, mode=INOUT, jdbcType=CHAR, javaType=java.lang.String}"
			+ ",#{_image, mode=INOUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{result_set, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_auth, mode=OUT, jdbcType=CHAR, javaType=java.lang.String}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void usersProcedure(Map<String, Object> params);
	
	@Insert(value = "CALL users_write_comment_procedure("
			+ "#{_commentId, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer }"			
			+ ",#{_email, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_content, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ")")
	public String writeCommentReply(Map<String, Object> params);
}
