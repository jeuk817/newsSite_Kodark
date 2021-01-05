package com.kodark.news.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.AuthProcedureMapper;

@Repository
public class AuthProcedureDao {
	
	@Autowired
	AuthProcedureMapper authProcedureMapper;
	
	public void excuAuthProcedure(Map<String, Object> params) {
		authProcedureMapper.authProcedure(params);
	}
<<<<<<< HEAD
}
=======
}
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
