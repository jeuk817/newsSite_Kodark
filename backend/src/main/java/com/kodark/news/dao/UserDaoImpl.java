package com.kodark.news.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired 
	private SqlSession sql;
	
	//회원가입
	@Override
	public void signUp(UserDto dto) throws Exception {
		sql.insert("Mapper.signUp", dto);
		
	}
	//로그인
	@Override
	public UserDto signIn(UserDto dto) throws Exception {
		return sql.selectOne("Mapper.signIn", dto);
		
	}
	
}
