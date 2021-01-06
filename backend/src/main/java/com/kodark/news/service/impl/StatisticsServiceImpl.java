package com.kodark.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.StatisticsProcedureDao;
import com.kodark.news.service.StatisticsService;


@Service
public class StatisticsServiceImpl implements StatisticsService{	
	
	@Autowired
	StatisticsProcedureDao statisticsDao;
	
	@Override
	public Map<String, Object> execuStatisticsProcedure(Map<String, Object> params) {		
		return statisticsDao.excuStatisticsProcedure(params);
	}

	@Override
	public List<Map<String, Object>> execuTodayPopularProcedure() {		
		return statisticsDao.excuTodayPopularProcedure();
	}

}
