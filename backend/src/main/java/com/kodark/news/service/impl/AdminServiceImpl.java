package com.kodark.news.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.AdminDao;
import com.kodark.news.dto.UserDto;
import com.kodark.news.service.AdminService;
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	@Qualifier("AdminDao")
	private AdminDao admindao;
	
	@Override
	public List<Map<String,Object>> getInfoReporters() {
		return admindao.getInfoReporters();
	}

}
