package com.kodark.news.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kodark.news.dto.TestDto;

@Mapper
public interface UserMapper {

	@Select("select * from emp")
	public List<TestDto> getEmps();
	
//	@Select("select * from emp where id = #{id}")
//	public TestDto getEmp(@Param("id") int id);
	
	@Insert("insert into user(email, pwd) values(#{email},#{pwd})")
	public void insertUser(@Param("email")String email, @Param("pwd")String pwd);
	
	@Delete("delete from emp where id = #{id}")
	public void deleteUser(@Param("id")int id);
}