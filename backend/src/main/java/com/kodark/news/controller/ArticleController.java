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
	
	@Autowired
	ArticleProcedureService articleProcedureService;
	
	 /**
	    * 기사 상세 페이지
	    * 작성자 : 이푸름 
	    * 작성일 : 2021-01-06
	  */
	@GetMapping
	public ResponseEntity<Map<String, Object>> getArticleDetail(@RequestParam("articleId") String articleId, HttpServletResponse response) {
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> temp;
		List<Map<String,Object>>list = new ArrayList<>();
		Map<String, Object> reporter = new HashMap<>();;
		

		
		int _articleId = Integer.parseInt(articleId);
		params.put("_articleId", _articleId);
		
		try {
			list = articleProcedureService.getArticleDetail(params);
			params.remove("_articleId");
			params.put("articleId", list.get(0).get("articleId"));
			params.put("title", list.get(0).get("title"));
			params.put("content", list.get(0).get("content"));
			params.put("createdAt", list.get(0).get("createdAt"));
			params.put("editedAt", list.get(0).get("editedAt"));
			params.put("hit", list.get(0).get("hit"));
			reporter.put("id", list.get(0).get("reporterId"));
			reporter.put("name", list.get(0).get("name"));
			reporter.put("email", list.get(0).get("email"));
			params.put("reporter", reporter);
			
			System.out.println(list.size());
			for(int i=0; i<list.size(); i++) {    
				temp = new HashMap<>();
				temp.put("image", list.get(i).get("image"));
				temp.put("imgDec", list.get(i).get("description"));
				temp.put("source", list.get(i).get("source"));
				
				System.out.println("temp:" +temp);
				System.out.println("list:"+list);
				list.set(i, temp);
			}	
			params.put("images", list);
			System.out.println(list);
			
			response.setHeader("Links",
					"</article/emotion?id\">; rel=\"emotion\""
							+"</article/comment?id\">; rel=\"comment\"");
			
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.NOT_FOUND);//404
		}
		return new ResponseEntity<Map<String, Object>> (params, HttpStatus.OK);
	}
	
}
