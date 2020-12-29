package com.kodark.news.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kodark.news.dto.TestDto;

@Mapper
public interface TestMapper {

	@Select("select * from emp")
	public List<TestDto> getEmps();
	
//	@Select("select * from emp where id = #{id}")
//	public TestDto getEmp(@Param("id") int id);
}
