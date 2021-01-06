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

import com.kodark.news.service.ReportersProcedureService;

@RestController
@RequestMapping(path = "/reporters")
public class ReporterController {
	
	@Autowired
	ReportersProcedureService reportersProcedureService;
	
	@GetMapping(path = "/navigation")
	public ResponseEntity<Map<String, Object>> reportNavi(HttpServletResponse response){
		/*
		 * String token = jwtService.createToken("jack", (2 * 1000 * 60));
		 * 
		 * Map<String, Object> map = new HashMap<>(); Cookie cookie = new Cookie("jwt",
		 * token); cookie.setMaxAge(7 * 24 * 60 * 60); cookie.setSecure(true);
		 * cookie.setHttpOnly(true); cookie.setPath("/");
		 * 
		 * response.addCookie(cookie);
		 */
		response.setHeader("Links",
								"</repoters?reporterId>; 					rel=\"reporterList\","
						  	  + "</reporters/new-post>; 	   				rel=\"articlePostForm\","
							  + "</reporters/article?status=\"published\">; rel=\"publishedArticleList\","
							  + "</reporters/article?status=\"waiting\"> ;  rel=\"waitingArticleList\",");
		
		Map<String, Object> params = new HashMap<>();	
		
		/*
		 * params.put("_switch", "navigation");p
		 * arams.put("_id", 4);
		 * params.put("_email", "bit@gmail.com");
		 */
		
		reportersProcedureService.execuReportersProcedure(params);
		
		System.out.println("파람스~~~~~~~~~~~~" + params);
		
		if(params.get("result_set").equals("204")){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}else if(params.get("result_set").equals("401")){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401
		}else if(params.get("result_set").equals("403")) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);//403
		}else if(params.get("result_set").equals("404")){		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500

	}
	
	  @GetMapping
	   public ResponseEntity<Map<String, Object>> getInfoProfile(@RequestParam Map<String, Object> body){
	      Map<String, Object> params = new HashMap<String, Object>();
	      params = reportersProcedureService.getReporterInfo(body);
	      
	      if(body.get("result_set").equals("200")) {
	         return new ResponseEntity<Map<String, Object>>(params,HttpStatus.OK);   
	      }else if(body.get("result_set").equals("404")) {
	         return new ResponseEntity<Map<String, Object>>(HttpStatus.NOT_FOUND);   
	      }else {
	         return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);   
	      }
	   }
	  
	  
	  /**
	    * 발행/발행대기 기사
	    * 작성자 : 이푸름 
	    * 작성일 : 2021-01-06
	  */
	  @GetMapping(path = "/article")
	  public ResponseEntity<List<Map<String, Object>>> pubAndWaitArtlcles(@RequestParam("status") String status, HttpServletResponse response){
		  
		  response.setHeader("Links",
					"</ko/reporters/article>; 					rel=\"editArticleForm\","
			  	  + "</reporters/article?status=\"deleted\">; 	rel=\"blindArticle\","
				  + "</reporters/article?articleId\">; rel=\"deleteArticle\","
				  + "</reporters/article/statics\"> ;  rel=\"articlestatics\","
				  + "</ko/article?articleId\">; rel=\"article\",");
				 
		  response.setHeader("_links",
				  	"["
				  	+ "{"
				  	+ "rel: \"editArticleForm\","
				  	+ "href : \"/ko/reporters/article\","
				  	+"method: \"get\","
				  	+ "},"
					+ "{"
				  	+ "rel: \"blindArticle\","
				  	+ "href : \"/reporters/article?status=deleted\","
				  	+"method: \"patch\","
				  	+ "},"
				  	+ "{"
				  	+ "rel: \"deleteArticle\","
				  	+ "href : \"/reporters/article?articleId\","
				  	+"method: \"delete\","
				  	+ "}"
				  	+ "]");
		  String _status = status;
		  if( reportersProcedureService.getPubAndWaitArtlcles(_status).get(1) == null) {
			  return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		  }
		  return new ResponseEntity<List<Map<String, Object>>> (reportersProcedureService.getPubAndWaitArtlcles(_status), HttpStatus.OK); //200
	  }
}
