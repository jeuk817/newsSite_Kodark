package com.kodark.news.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kodark.news.dto.UserDto;

public interface UserMapper {
	
	//회원 이메일로 검색
	@Select("SELECT *FROM USER WHERE email =#{email}")
	public void selectUser(String email);
	
	//회원 전체 검색
	@Select("SELECT *FROM USER")
	public List<UserDto> selectAllUser();
	
	//회원 정보 입력
	@Insert("INSERT INTO USER VALUES(#{email}, #{pwd}")
	public void intertUser(UserDto user);
	
	//회원 상세정보 입력
	@Insert("INSERT INTO USER_DETAIL VALUES(#{nick_Name}, #{name}, #{local}, #{birth}, #{gender}, #{image})")
	public void insertUserDetail(UserDto user);
	
	//회원 비밀번호 수정
	@Update("UPDATE USER SET pwd=#{pwd}")
	public void updatePassword(String pwd);
	
	//회원 상세정보 수정(수정 내용: 닉네임, 지역, 생일, 성별, 이미지, [이름은 제외])
	@Update("UPDATE USER SET nick_Name=#{nick_Name}, local=#{local}, birth=#{birth}, gender=#{gender}, image=#{image}")
	public void updateUserDetail(UserDto user);
	
	/*
	유저 삭제/정지 구문(일단 이건 다같이 의논하고 정하기)
	public void updateUserStatus(UserDto user);
	public void deleteUser(String email);
	*/
}
