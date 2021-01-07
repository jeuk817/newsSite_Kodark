package com.kodark.news.controller;

import java.util.ArrayList;
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

import com.kodark.news.dto.CategoryDto;
import com.kodark.news.service.ArticleProcedureService;

@RestController
@RequestMapping(path = "/article")
public class ArticleController {
	
	@Autowired
	private ArticleProcedureService articleProcedureService;
	
	/**
	 * 메인네비 정보
	 * 작성자 : 최현지
	 * 작성일 : 2021-01-06
	 */
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
	
	/**
	 * 핫 뉴스 (popular)
	 * 작성자 : 최현지
	 * 작성일 : 2021-01-07
	 */
	@GetMapping(path = "/popular")
	public ResponseEntity<Map<String,Object>> hotNews(HttpServletResponse response){
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> temp; 
		Map<String, Object> link;
		List<Map<String,Object>>list = new ArrayList<>();
		
		
		try {
			list=articleProcedureService.hotNews();
			
			for(int i=0;i<list.size();i++) {		
				temp = new HashMap<>();
				link = new HashMap<String, Object>();
				  
				link.put("rel", "article");
				link.put("href", "article?articleId=" + list.get(i).get("id"));
				link.put("method ", "get");
				
				temp.put("id",list.get(i).get("id"));
				temp.put("title", list.get(i).get("title"));
				temp.put("content", list.get(i).get("content"));
				temp.put("image", list.get(i).get("image"));
				temp.put("imgDec", list.get(i).get("imgDec"));
				temp.put("_link", link);		
				
				list.set(i, temp);				
			}
			
			params.put("category", "all");
			params.put("type", "popular");	
			params.put("data", list);	
			response.setHeader("Links","rel : \"article\","
									 + "href : \"/article?articleId\","
									 + "method : \"get\"");
		
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(params, HttpStatus.INTERNAL_SERVER_ERROR);//500
		}	
		return new ResponseEntity<Map<String,Object>>(params,HttpStatus.OK);//200
	}
	
	
	/**
	 * 카테고리 정보
	 * 작성자 : 최현지
	 * 작성일 : 2021-01-06
	 */
	//카테고리 정보
	@GetMapping(path = "/category" )
	public ResponseEntity<List<CategoryDto>> categoryInfo(){

		return new ResponseEntity<List<CategoryDto>>(articleProcedureService.categoryInfo(), HttpStatus.OK);//200 
	}
}
