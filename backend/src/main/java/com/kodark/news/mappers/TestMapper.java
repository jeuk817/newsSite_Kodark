package com.kodark.news.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.kodark.news.dto.TestDto;

@Mapper
public interface TestMapper {

	@Select("select * from emp")
	public List<TestDto> getEmps();
	
//	@Select("select * from emp where id = #{id}")
//	public TestDto getEmp(@Param("id") int id);
	
	@Insert("insert into emp(id ,name ,age) values(1,'name',12)")
	public void insertUser();
	
	@Delete("delete from emp where id = #{id}")
	public void deleteUser(@Param("id")int id);
	
	@Select(value = "{CALL emp_procedure("
			+ "#{name, mode=IN, jdbcType=VARCHAR, javaType=java.lang.String}"
//			+ "#,{age, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"
//			+ "#,{id, mode=OUT, jdbcType=INTEGER, javaType=java.lang.Integer}"
//			+ "#{name, mode=OUT, javaType=java.lang.String}"
//			+ "#{age, mode=OUT, javaType=java.lang.Integer},"
			+ ")}")
	@Options(statementType = StatementType.CALLABLE)
	public void empProcedure(@Param("name") String name, @Param("age") int age);
}
