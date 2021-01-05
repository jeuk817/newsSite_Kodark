package com.kodark.news.service.impl;

import java.util.Map; 


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.ReporterDao;
import com.kodark.news.service.ReporterService;

@Service
public class ReporterServiceImpl implements ReporterService {
	
	@Autowired
	private ReporterDao reporterdao;

	@Override
	public Map<String, Object> getReporterInfo(int id) {
		return reporterdao.getReporterInfo(id);
	}
	
	
}
