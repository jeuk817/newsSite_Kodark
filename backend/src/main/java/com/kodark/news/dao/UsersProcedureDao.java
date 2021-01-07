package com.kodark.news.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.UsersProcedureMapper;

@Repository
public class UsersProcedureDao {
	
	@Autowired
	UsersProcedureMapper usersProcedureMapper;
	
	public void excuUsersProcedure(Map<String, Object> params) {
		usersProcedureMapper.usersProcedure(params);
	}
}
