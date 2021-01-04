package com.kodark.news.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodark.news.dao.AdminDao;
import com.kodark.news.dto.CategoryDto;
import com.kodark.news.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired	
	private AdminDao adminDao;

	
	@Override
	public List<CategoryDto> CategoryInfo() {
		
		List<CategoryDto> category = adminDao.categoryInfo();
		
		return category;
	}

	
}
