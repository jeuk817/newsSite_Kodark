package com.kodark.news.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.kodark.news.service.ReportersProcedureService;

@RestController
@RequestMapping(path = "/reporters")
public class ReporterController {
	
	//기자 네비게이션
	@GetMapping(path = "/navigation")
	public ResponseEntity<Map<String, Object>> reporterNavi(HttpServletResponse response){
		
		response.setHeader("Links",
								"</repoters?reporterId>; 					rel=\"reporterList\","
						  	  + "</reporters/new-post>; 	   				rel=\"articlePostForm\","
							  + "</reporters/article?status=\"published\">; rel=\"publishedArticleList\","
							  + "</reporters/article?status=\"waiting\"> ;  rel=\"waitingArticleList\"");
	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		
	}

}