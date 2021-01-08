package com.kodark.news.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.kodark.news.dto.CategoryDto;

@Mapper
public interface ArticleProcedureMapper {
	
	@Select(value = "{CALL article_emotion_Info_procedure("
			+ "#{_articleId, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"		
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> getEmotionInfo(@Param("_articleId") int _articleId);
	
	@Select(value = "{CALL article_comment_reply_procedure("
			+ "#{_articleId, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ ",#{_commentId, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"	
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> getCommentReply(@Param("_articleId") int _articleId,@Param("_commentId") int _commentId);

	@Select("select * from category")
	public  List<CategoryDto> getCategory();

	@Select(value = "{CALL hotNews_procedure()}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> getHotNews();
	
	@Select(value = "{CALL latest_procedure("
			+ "#{category, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"			
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> latestProcedure(Map<String, Object> params);
	
	@Select(value = "{CALL comment_procedure("
			+ "#{_article_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> commentProcedure(int i);
	
	@Select(value = "{CALL article_procedure("
			+ "#{_article_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"
			+ ", #{_reporter_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"		
			+ ", #{result_set, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"		
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public Map<String, Object> articleBlindProcedure(Map<String, Object> params);
	
}
