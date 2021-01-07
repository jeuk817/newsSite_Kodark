package com.kodark.news.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.service.StatisticsService;
import com.kodark.news.dto.UserDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kodark.news.dto.ArticleDto;
import com.kodark.news.service.AdminProcedureService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	

	@Autowired
	StatisticsService statisticsService;
	
	@Autowired 
	AdminProcedureService adminProcedureService;
	
	@GetMapping(path = "/statistics")
	public ResponseEntity<List<Map<String, Object>>> mainPage(){
		System.out.println("ck");			
		int _id = 1;		
		System.out.println(statisticsService.execuStatisticsProcedure(_id));
		return new ResponseEntity<List<Map<String, Object>>>(statisticsService.execuStatisticsProcedure(_id),HttpStatus.OK);//200
	}

	/**
	    * 발행 대기중 기사
	    * 작성자 : 이푸름 
	    * 작성일 : 2021-01-05
	    * 
	  */
	@GetMapping(path="/article")
	public ResponseEntity <List<Map<String, Object>>> waitingArticle(@RequestParam("status") String status, HttpServletResponse response) {
		String _status = status;
		List<Map<String,Object>>list = new ArrayList<>();
		Map<String, Object> container;
		Map<String, Object> reporter;
		Map<String, Object> article;
		Map<String, Object> link;
		list = adminProcedureService.getWaitArticles(_status);
		
		for(int i=0; i<list.size(); i++) {
			reporter =  new HashMap<>();
			article = new HashMap<>();
			container = new HashMap<>();
			link = new HashMap<>();
			
			link.put("rel", "waitingArticleDetail");
			link.put("href", "/admin/article?articleId&status=waiting");
			link.put("method", "get");
			
			reporter.put("name", list.get(i).get("name"));
			reporter.put("email", list.get(i).get("email"));
			
			article.put("id", list.get(i).get("id"));
			article.put("title", list.get(i).get("title"));
			
		
			container.put("reporter", reporter);
			container.put("article", article);
			container.put("_link", link);
			list.set(i, container);
		}
		
		response.setHeader("Links",
				"</admin/article?articleId&status=\"waiting\">; rel=\"waitingArticleDetail\"");
		if(adminProcedureService.getWaitArticles(_status).get(1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}else if(adminProcedureService.getWaitArticles(_status).get(1) != null){
			return new ResponseEntity<List<Map<String, Object>>> (list, HttpStatus.OK); // 201;
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	}
	
	
	  /**
	    * 기자 아이디 생성
	    * 작성자 : 이푸름 
	    * 작성일 : 2021-01-05
	  */
	@PostMapping(path="/reporters")
	public ResponseEntity<UserDto> createReporter(@RequestBody Map<String, Object> body) throws ParseException {
		String email = (String) body.get("email");
		String pwd = (String)body.get("pwd");
		String auth = "reporter";
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "create_reporter");
		params.put("_email", email);
		params.put("_pwd", pwd);
		params.put("_auth", auth);
		String nickName = (String)body.get("nickname");
		String name = (String)body.get("name");
		String local = (String)body.get("local");
		
		String Stringbirth = (String) body.get("birth");
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date birth =  java.sql.Date.valueOf(Stringbirth);
		
		String gender = (String)body.get("gender");
		String image = (String)body.get("image");
		params.put("_nickName", nickName);
		params.put("_name", name);
		params.put("_local", local);
		params.put("_birth", birth);
		params.put("_gender", gender);
		params.put("_image", image);
	
		adminProcedureService.execuAdminProcedure(params);
		if(params.get("result_set").equals("conflict")) {
			return new ResponseEntity<> (HttpStatus.CONFLICT); // 409
		}
		
		return new ResponseEntity<> (HttpStatus.CREATED); // 201
		
	}
	
	//관리자 네비게이션
	 @GetMapping(path = "/navigation")
	   public ResponseEntity<Map<String, Object>> reportNavi(HttpServletResponse response) {
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
	            "</admin/statistics>; rel=\"statistics\","
	                  + "</admin/users?userStartId>; rel=\"userList\","
	                  + "</admin/question-list?questionStartId&status=\"all\">; rel=\"allQuestionList\","
	                  + "</admin/report/comment>; rel=\"commentReportList\","
	                  + "</admin/report/article>; rel=\"articleReportList\","
	                  + "</admin/reporters>; rel=\"reporterList\","
	                  + "</admin/article/waiting>; rel=\"waitingArticleList\",");

	      Map<String, Object> params = new HashMap<>();
	      
	       params.put("_switch", "navigation");
	       params.put("_id", 34);
	       params.put("_email", "admin@naver.com");
	       
	       

	      adminProcedureService.execuAdminProcedure(params);

	      System.out.println("파람스~~~~~~~~~~~~" + params);

	      if (params.get("result_set").equals("204")) {
	         return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 204
	      } else if (params.get("result_set").equals("401")) {
	         return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);// 401
	      } else if (params.get("result_set").equals("403")) {
	         return new ResponseEntity<>(HttpStatus.FORBIDDEN);// 403
	      } else
	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 500

	   }

}
	
