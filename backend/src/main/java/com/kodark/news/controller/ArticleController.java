package com.kodark.news.controller;

import java.util.HashMap;
import java.util.List;
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
	ArticleProcedureService articleProcedureService;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> getArticleDetail(@RequestParam("articleId") String articleId) {
		
		int _articleId = Integer.parseInt(articleId);
		if(articleId == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String, Object>> (articleProcedureService.getArticleDetail(_articleId), HttpStatus.OK); // 200;
	
	}
}
