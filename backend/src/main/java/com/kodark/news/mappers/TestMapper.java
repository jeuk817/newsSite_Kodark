package com.kodark.news.mappers;

import java.util.List;
import java.util.Map;

<<<<<<< HEAD

=======
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.kodark.news.dto.TestDto;

@Mapper
public interface TestMapper {

	@Select("select * from emp")
	public List<TestDto> getEmps();
	
<<<<<<< HEAD

=======
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
	@Select("select * from emp where id = #{id}")
	public TestDto getEmp(@Param("id") int id);
	
	@Select(value = "{CALL emp_procedure("
			+ "#{_name, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String},"
			+ "#{_age, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer},"
			+ "#{_id, mode=INOUT, jdbcType=INTEGER, javaType=java.lang.Integer}"
<<<<<<< HEAD

=======
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
//			+ "#{name, mode=OUT, javaType=java.lang.String}"
//			+ "#{age, mode=OUT, javaType=java.lang.Integer},"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
<<<<<<< HEAD

=======
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
//	@ResultType(TestDto.class)
//	public String empProcedure(@Param("name") String name);
//	public void empProcedure(TestDto testDto);
	public void empProcedure(Map<String, Object> parameters);
<<<<<<< HEAD

=======
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
}
