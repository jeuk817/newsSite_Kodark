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

import com.kodark.news.service.ArticleProcedureService;

@RestController
@RequestMapping(path = "/article")
public class ArticleController {
	
	
	
	private ArticleProcedureService articleProcedureService;
	
	@Autowired
	public ArticleController(ArticleProcedureService articleProcedureService) {		
		this.articleProcedureService = articleProcedureService;
	}
	/**
	 * 섹션별 최신기사(10개)
	 * 작성자 : 최윤수
	 * 작성일 : 2021-01-06
	 */
	@GetMapping(path = "/latest")
	public ResponseEntity<Map<String,Object>> latest(@RequestParam(value = "section", required = false, defaultValue = "politics")String category, HttpServletResponse response){
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> temp = new HashMap<>();
		Map<String,Object> link = new HashMap<>();
		List<Map<String,Object>>list = new ArrayList<>();		
		//StringBuffer sb = new StringBuffer();	
		params.put("category", category);
		System.out.println("check");
	try {
			list=articleProcedureService.execuLatestProcedure(params);
			params.put("type", "latest");				
			for(int i=0;i<list.size();i++) {
				temp = new HashMap<>();
				link = new HashMap<>();
				link.put("rel","article");
				link.put("href","/article?articleId="+list.get(i).get("id"));
				link.put("method","get");
				//sb.append("rel :\"article\", href : \"article?articleId="+list.get(i).get("id")+"\",method : \"get\"");
				temp.put("id",list.get(i).get("id"));
				temp.put("title", list.get(i).get("title"));
				temp.put("content", list.get(i).get("content"));
				temp.put("image", list.get(i).get("image"));
				temp.put("imgDec", list.get(i).get("imgDec"));				
				temp.put("_link", link);		
				list.set(i,temp);				
			//	sb.delete(0, sb.length());			
		}
			params.put("data", list);
			
		
			response.setHeader("Links","rel : \"article\","
									 + "href : \"/article?articleId\","
									 + "method : \"get\"");
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NOT_FOUND);//404
		}	
		return new ResponseEntity<Map<String,Object>>(params,HttpStatus.OK);//200
	}
	/**
	 * 기사댓글 데이터
	 * 작성자 : 최윤수 
	 * 작성일 : 2021-01-07
	 */
	@GetMapping(path = "/comment")
	public ResponseEntity<List<Map<String, Object>>> comment(@RequestParam(value = "articleId", required = false, defaultValue = "1")int articleId,
															@RequestParam(value = "commentStartId", required = false, defaultValue = "1")int commentStartId
			){
		Map<String, Object> params = new HashMap<>();
		List<Map<String,Object>>list = new ArrayList<>();
		articleId = 3;
			
		try {
		list=articleProcedureService.execuCommentProcedure(articleId);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		System.out.println("lsit :"+list);
		params.put("data", list);		
		return new ResponseEntity<List<Map<String,Object>>>(list,HttpStatus.OK);//200
	}
	
}
