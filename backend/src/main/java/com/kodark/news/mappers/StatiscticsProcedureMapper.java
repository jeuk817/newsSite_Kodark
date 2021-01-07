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
			+ ",#{weeklyHits, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ",#{weeklyArticleCount, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ",#{totalUser, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ",#{totalReporter, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ",#{monthlyIncUser, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ",#{monthlyDecUser, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ",#{result_set, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String}"			
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public Map<String, Object> statiscticsProcedure(Map<String, Object> params);
	
	@Select(value = "{CALL todayPopular_procedure()}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> todayPopularProcedure();
	
	@Select(value = "{CALL articleStatistics_procedure("
			+ "#{_article_id, mode=IN, jdbcType=INTEGER, javaType=java.lang.Integer}"			
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public List<Map<String, Object>> articleStatisticsProcedure(int articleId);
	
}
