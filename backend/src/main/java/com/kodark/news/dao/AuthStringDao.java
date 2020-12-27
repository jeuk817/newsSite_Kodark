package com.kodark.news.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.kodark.news.dto.UserDto;

public class AuthStringDao {
		@Autowired
	    private SqlSessionTemplate sql;
		private String nameSpace = "com.kodark.news.mappers.UserMapper";
	 
	    //해당 email에 인증 키 업데이트
	    public void updateAuthKey(UserDto dto) throws Exception {
	        UserDto dto2 = new UserDto();
	        dto2.setAuth(dto.getAuth());
	        dto2.setEmail(dto.getEmail());
	       
	        sql.update(nameSpace + ".updateAuthKey", dto2);
	    }
	  

	    //이메일 인증 코드 확인
	    public UserDto authVerify(UserDto dto) throws Exception {
	        return sql.selectOne(nameSpace + ".authCheck", dto);
	    }

	    
}
