package com.kodark.news.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.service.ReporterProcedureService;

@RestController
@RequestMapping(path = "/reporters")
public class ReporterController {

	@Autowired
	ReporterProcedureService reporterProcedureService;
	
	@GetMapping
	public ResponseEntity <Map<String, Object>> getReporterInfo() {
		
		int _id = 26;
		return new ResponseEntity<Map<String, Object>>(reporterProcedureService.getReporterInfo(_id), HttpStatus.CREATED); 
	}
	
	
}
