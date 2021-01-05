package com.kodark.news.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kodark.news.dto.UserDto;
import com.kodark.news.mappers.AdminMapper;
import com.kodark.news.mappers.UserMapper;


@Repository("AdminDao")
public class AdminDao {
	
	@Autowired
	private AdminMapper adminmapper;
	
	
	public List<Map<String,Object>> getInfoReporters(){
		System.out.println("eee");
		return adminmapper.getInfoReporters();
	}
}
