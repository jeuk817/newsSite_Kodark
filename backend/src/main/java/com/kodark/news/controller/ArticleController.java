package com.kodark.news.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.service.ArticleProcedureService;

@RestController
@RequestMapping(path = "/article")
public class ArticleController {
	
	@Autowired
	ArticleProcedureService articleProcedureServie;
	
	@GetMapping(path = "/latest")
	public ResponseEntity<Map<String,Object>> latest(@RequestParam(value="section",required=false, defaultValue="politics")String section){
		Map<String, Object> params = new HashMap<>();
		section = "politics";
		params.put("section", section);
		articleProcedureServie.execulatestProcedure(params);
		if(params.get("result_set").equals("404")) {
			return new ResponseEntity<Map<String,Object>>(params,HttpStatus.NOT_FOUND);//404
		}else
		return new ResponseEntity<Map<String,Object>>(params,HttpStatus.OK);//200
	}
}
