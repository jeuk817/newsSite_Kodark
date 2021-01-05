package com.kodark.news.controller;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.service.ReporterService;

@RestController
@RequestMapping(path = "/reporters")
public class ReporterController {
	
	@Autowired
	private ReporterService reporterserivce;
	
	@GetMapping
	public ResponseEntity<Map<String,Object>> getReporterInfo(@RequestParam("_id") int _id) {
		return new ResponseEntity<Map<String,Object>>(reporterserivce.getReporterInfo(_id), HttpStatus.OK);
		
	}
	
	
}
