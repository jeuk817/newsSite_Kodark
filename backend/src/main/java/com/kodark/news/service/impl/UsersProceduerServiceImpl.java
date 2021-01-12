package com.kodark.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.UsersProcedureDao;
import com.kodark.news.service.UsersProceduerService;

@Service
public class UsersProceduerServiceImpl implements UsersProceduerService {

	@Autowired
	UsersProcedureDao usersProcedureDao;

	@Override
	public void execuUsersProcedure(Map<String, Object> params) {
		usersProcedureDao.execuUsersProcedure(params);

	}

	@Override
	public String writeCommentReply(Map<String, Object> params) {
		return usersProcedureDao.writeCommentReply(params);
	}

	@Override
	public Map<String, Object> myPage(Map<String, Object> params) {
		return usersProcedureDao.myPage(params);
	}

	@Override
	public List<Map<String, Object>> subList(Map<String, Object> params) {		
		return usersProcedureDao.subList(params);
	}

}
