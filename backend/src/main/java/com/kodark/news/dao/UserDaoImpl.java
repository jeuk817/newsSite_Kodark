package com.kodark.news.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.dto.UserDto;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired 	
	private SqlSession sql;
	private String nameSpace = "com.kodark.news.mappers.UserMapper";
	
	//회원가입
	@Override
	public void signUp(UserDto dto) throws Exception {

		sql.insert(nameSpace + ".singUp", dto);

		
	}
	//로그인
	@Override
	public UserDto signIn(UserDto dto) throws Exception {
		return sql.selectOne(nameSpace + ".signIn", dto);

	}
	
	//email 중복 확인
	@Override
    public int emailCheck(UserDto dto) throws Exception {
        int result =  sql.selectOne(nameSpace + ".emailCheck", dto);
        
        return result;
    }
 
	
}
