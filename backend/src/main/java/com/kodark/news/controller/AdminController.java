package com.kodark.news.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.service.StatisticsService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	
	@Autowired
	StatisticsService statisticsService;
	
	
	
	@GetMapping(path = "/statistics")
	public ResponseEntity<List<Map<String, Object>>> mainPage(){
		System.out.println("ck");			
		int _id = 1;		
		System.out.println(statisticsService.execuStatisticsProcedure(_id));
		return new ResponseEntity<List<Map<String, Object>>>(statisticsService.execuStatisticsProcedure(_id),HttpStatus.OK);//200
	}

}
