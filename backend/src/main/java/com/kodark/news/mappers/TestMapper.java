package com.kodark.news.mappers;

import java.util.List;
<<<<<<< HEAD

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
=======
import java.util.Map;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b

import com.kodark.news.dto.TestDto;

@Mapper
public interface TestMapper {

	@Select("select * from emp")
	public List<TestDto> getEmps();
	
<<<<<<< HEAD
<<<<<<< HEAD
//	@Select("select * from emp where id = #{id}")
//	public TestDto getEmp(@Param("id") int id);
=======
=======

>>>>>>> 7a50b4c9d0ff0a4dc2b9aace164a0d9284f5b9cc
	@Select("select * from emp where id = #{id}")
	public TestDto getEmp(@Param("id") int id);
	
	@Select(value = "{CALL emp_procedure("
			+ "#{_name, mode=OUT, jdbcType=VARCHAR, javaType=java.lang.String},"
			+ "#{_age, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer},"
			+ "#{_id, mode=INOUT, jdbcType=INTEGER, javaType=java.lang.Integer}"

//			+ "#{name, mode=OUT, javaType=java.lang.String}"
//			+ "#{age, mode=OUT, javaType=java.lang.Integer},"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)

//	@ResultType(TestDto.class)
//	public String empProcedure(@Param("name") String name);
//	public void empProcedure(TestDto testDto);
	public void empProcedure(Map<String, Object> parameters);
<<<<<<< HEAD
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
=======

>>>>>>> 7a50b4c9d0ff0a4dc2b9aace164a0d9284f5b9cc
}
