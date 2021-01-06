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
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.service.StatisticsService;
import com.kodark.news.dto.UserDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.kodark.news.service.AdminProcedureService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	

	@Autowired
	StatisticsService statisticsService;
	
	@Autowired 
	AdminProcedureService adminProcedureService;
	 
	//기자목록(이종현 restAPI 57_line)
	@GetMapping(path ="/reporters")
	public ResponseEntity <List<Map<String, Object>>> getReportersList(){
		return new ResponseEntity<List<Map<String,Object>>>(adminProcedureService.getReporterList(),HttpStatus.OK);
	}

	//기사작성대기

	@GetMapping(path = "/statistics")
	public ResponseEntity<List<Map<String, Object>>> mainPage(){
		System.out.println("ck");			
		int _id = 1;		
		System.out.println(statisticsService.execuStatisticsProcedure(_id));
		return new ResponseEntity<List<Map<String, Object>>>(statisticsService.execuStatisticsProcedure(_id),HttpStatus.OK);//200
	}

	//발행대기중 기사
	@GetMapping(path="/article")
	public ResponseEntity <List<Map<String, Object>>> waitingArticle() {
		return new ResponseEntity<List<Map<String, Object>>> (adminProcedureService.getWaitArticles(), HttpStatus.CREATED); // 201;
	}
	
	//
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
		
		return new ResponseEntity<> (HttpStatus.CREATED); // 201
		
	}
	
	//愿�由ъ옄 �꽕鍮꾧쾶�씠�뀡
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
	
