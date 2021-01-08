package com.kodark.news.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.Mail;
import com.kodark.news.dto.UserDto;
import com.kodark.news.service.AdminProcedureService;
import com.kodark.news.service.MailService;
import com.kodark.news.service.StatisticsService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {
	
	Environment env;	
	MailService mailService;		
	StatisticsService statisticsService;	
	AdminProcedureService adminProcedureService;

	@Autowired
	public AdminController(MailService mailService, StatisticsService statisticsService,
			AdminProcedureService adminProcedureService,Environment env) {
		this.env = env;
		this.mailService = mailService;
		this.statisticsService = statisticsService;
		this.adminProcedureService = adminProcedureService;
		
	}

	/**
	 * 관리자메인
	 * 작성자 : 최윤수 
	 * 작성일 : 2021-01-06
	 */
	@GetMapping(path = "/statistics")
	public ResponseEntity<Map<String, Object>> mainPage(){
		List<Map<String,Object>>list = new ArrayList<>();	
		Map<String, Object> params = new HashMap<>();
		params.put("_id", 1);
		statisticsService.execuStatisticsProcedure(params);			
		list = statisticsService.execuTodayPopularProcedure();	
		params.put("todayPopular", list);
		System.out.println("result");
		return new ResponseEntity<Map<String, Object>>(params,HttpStatus.OK);//200
	}

	//발행대기중 기사
	@GetMapping(path="/article")
	public ResponseEntity <List<Map<String, Object>>> waitingArticle() {
		
		return new ResponseEntity<List<Map<String, Object>>> (adminProcedureService.getWaitArticles(), HttpStatus.CREATED); // 201;
	}
	
	//기자아이디 생성
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
	
	//관리자 네비정보
	@GetMapping(path = "/navigation")
	public ResponseEntity<Map<String, Object>> adminNavi(HttpServletResponse response) {
		
		response.setHeader("Links",
						  "</admin/statistics>; 									rel=\"statistics\","
						+ "</admin/users?userStartId>; 	   							rel=\"userList\","
						+ "</admin/question-list?questionStartId&status=\"all\">;	rel=\"allQuestionList\","
						+ "</admin/report/comment>; 								rel=\"commentReportList\","
						+ "</admin/report/article>; 								rel=\"articleReportList\","
						+ "</admin/reporters>;  									rel=\"reporterList\","
						+ "</admin/article/waiting>;  								rel=\"waitingArticleList\"");
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 204

	}
	
	/**
	 * 기자에게 이메일보내기
	 * 작성자 : 최윤수
	 * 작성일 :2021-01-07
	 */
	@PostMapping(path = "/reporters/email")
	public ResponseEntity<Map<String, Object>> sendMailToReporter(@RequestBody Map<String, Object> body){		
		String email = (String) body.get("email");
		String title = (String) body.get("title");	
		String content = (String) body.get("content");			
		Mail mail = new Mail();	
		try {
			mail.setMailFrom(env.getProperty("email.username"));
			mail.setMailTo(email);
			mail.setMailSubject(title);
			mail.setMailContent(content);
			mailService.sendMail(mail);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//500
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
	}
	
}