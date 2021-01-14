package com.kodark.news.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.mappers.UsersProcedureMapper;

@Repository
public class UsersProcedureDao {

	@Autowired
	UsersProcedureMapper usersProcedureMapper;

	public void execuUsersProcedure(Map<String, Object> params) {
		usersProcedureMapper.usersProcedure(params);
	}

	public String writeCommentReply(Map<String, Object> params) {
		return usersProcedureMapper.writeCommentReply(params);
	}

	public Map<String, Object> myPage(Map<String, Object> params) {
		return usersProcedureMapper.getMyPage(params);

	}

	public Map<String, Object> myPageDetail(Map<String, Object> params) {
		return usersProcedureMapper.getMyPageDetail(params);
	}
}
