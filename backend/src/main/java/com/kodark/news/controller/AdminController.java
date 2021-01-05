package com.kodark.news.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.UserDto;
import com.kodark.news.service.AdminService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	
	@Autowired
	AdminService adminservice;
	
	//기자목록
	@GetMapping(path ="/reporters")
	public ResponseEntity<List<Map<String, Object>>> reporters(){
		return new ResponseEntity<List<Map<String, Object>>>(adminservice.getInfoReporters(),HttpStatus.OK);//200
	}

}
