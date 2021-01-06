package com.kodark.news.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface ArticleProcedureMapper {

	
	@Select(value = "{CALL article_procedure("
			+ "#{_switch, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer }"			
			+ ",#{_title, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_content, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_createdAt, mode=OUT, jdbcType=DATE, javaType=java.util.Date}"
			+ ",#{_editedAt, mode=OUT, jdbcType=DATE, javaType=java.util.Date}"
			+ ",#{_hit, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ ",#{_image, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_imgDec, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_source, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{_report_id, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ ",#{_email, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ",#{result_set, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	void articleProcedure(Map<String, Object> params);
	
	@Select(value = "{CALL article_getArticleDetail(#{_articleId})}")
	@Options(statementType = StatementType.CALLABLE)
	public Map<String, Object> getArticleDetail(@Param("_articleId") int articleId);

}
