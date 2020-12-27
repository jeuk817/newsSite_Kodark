package com.kodark.news.dao;


import com.kodark.news.dto.AuthStringDto;
import com.kodark.news.dto.UserDto;



public interface UserDao {

     //회원가입
     public void signUp(UserDto dto) throws Exception;  
    

     //로그인
     public UserDto signIn(UserDto dto) throws Exception; 
  
     //이메일 체크
     public int emailCheck(UserDto dto) throws Exception;
}