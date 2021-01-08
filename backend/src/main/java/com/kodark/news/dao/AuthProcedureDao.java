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

}
