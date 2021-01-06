package com.kodark.news.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ArticleProcedureDao;
import com.kodark.news.service.ArticleProcedureService;
@Service
public class ArticleProcedureServiceImpl implements ArticleProcedureService{
	
	@Autowired
	ArticleProcedureDao articleProcedureDao;
	@Override
	public Map<String,Object> execulatestProcedure(Map<String, Object> params) {
		return articleProcedureDao.latestProcedure(params);
	}

}
