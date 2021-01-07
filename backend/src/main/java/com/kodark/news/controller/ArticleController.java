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
	private ArticleProcedureService articleProcedureService; 
	
	
	@GetMapping(path ="/emotion")
	public ResponseEntity<List<Map<String, Object>>> getEmotionInfo(@RequestParam("articleId") int articleId
																			, HttpServletResponse response){
		
		List<Map<String, Object>> params = null;
		Map<String, Object> map = null;
		StringBuffer sb = null;
		int pSize = 0;
		
		try {
			params = articleProcedureService.execuArticleProcedure(articleId);
			sb = new StringBuffer();
			pSize = params.size();
			
			for(int i=0; i<pSize; i++) {
				map = new HashMap<String, Object>();
				map.put("href","/article/emotion?articleId=" + articleId + "&emotion="+params.get(i).get("emotion"));
				map.put("method", "put");
				map.put("rel", params.get(i).get("emotion"));		
				params.get(i).put("_link", map);
				} 
			for(int i=0; i<pSize; i++) {
				sb.append("</article/emotion?articleId="+articleId
						+"&emotion="+params.get(i).get("emotion")
						+">;" 
						+"rel="+ params.get(i).get("emotion")
						);
				if(pSize>1) {
					sb.insert(sb.length(), ",");
				}
			}
			System.out.println(sb.toString().substring(sb.length()-1));
			System.out.println(sb.toString().substring(sb.length()-1).equals(","));
			if(sb.toString().substring(sb.length()-1).equals(",")) {
				String str = sb.toString().substring(0, sb.length()-1);
				sb = new StringBuffer(str);			
			}
			response.setHeader("Links", sb.toString());	
			
			
			
			if(params.isEmpty()) {
				return new ResponseEntity<List<Map<String, Object>>>(params,HttpStatus.NO_CONTENT); //404
			}
			return new ResponseEntity<List<Map<String, Object>>>(params,HttpStatus.OK); //200
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.INTERNAL_SERVER_ERROR); //500
		}
	}
}
