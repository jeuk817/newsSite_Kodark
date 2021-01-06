package com.kodark.news.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
	
	 /**
	    * 기사 상세 페이지
	    * 작성자 : 이푸름 
	    * 작성일 : 2021-01-06
	  */
	@GetMapping
	public ResponseEntity<Map<String, Object>> getArticleDetail(@RequestParam("articleId") String articleId, HttpServletResponse response) {
		
		 response.setHeader("Links",
				  "</article/emotion?id\">; rel=\"emotion\""
				  +"</article/comment?id\">; rel=\"comment\"");
		 
		int _articleId = Integer.parseInt(articleId);
		if(articleProcedureService.getArticleDetail(_articleId) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Map<String, Object>> (articleProcedureService.getArticleDetail(_articleId), HttpStatus.OK); // 200;
	
	}
}
