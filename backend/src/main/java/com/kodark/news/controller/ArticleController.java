package com.kodark.news.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.CategoryDto;
import com.kodark.news.service.ArticleProcedureService;

@RestController
@RequestMapping(path = "/article")
public class ArticleController {
	
	@Autowired
	private ArticleProcedureService articleProcedureService;
	
	//메 네비게이션
	@GetMapping(path = "/navigation")
	public ResponseEntity<Map<String, Object>> mainNavi(HttpServletResponse response){
			
		response.setHeader("Links",
								"</auth/sign-in>; 		rel=\"signIn\","
						  	  + "</>; 	   				rel=\"home\","
							  + "</section/politics>;	rel=\"politics\","
							  + "</section/economy>;  	rel=\"economy\","
							  + "</section/society>;  	rel=\"society\","
							  + "</section/tech>; 		rel=\"tech\","
							  + "</section/world>;  	rel=\"world\","
							  + "</section/sports>;  	rel=\"sports\","
							  + "</weather>;  			rel=\"weather\","
							  + "</help>;  				rel=\"help\","
							  + "</introduce>;  		rel=\"introduce\"");		
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
			
	}
	

	//카테고리 정보
	@GetMapping(path = "/category" )
	public ResponseEntity<List<CategoryDto>> categoryInfo(){

		return new ResponseEntity<List<CategoryDto>>(articleProcedureService.CategoryInfo(), HttpStatus.OK);//200 
	}
}
