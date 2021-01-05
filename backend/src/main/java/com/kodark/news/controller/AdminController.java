package com.kodark.news.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.service.AdminProcedureService;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

	@Autowired
	AdminProcedureService adminProcedureService;

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
				"</admin/statistics>; 									rel=\"statistics\","
						+ "</admin/users?userStartId>; 	   						rel=\"userList\","
						+ "</admin/question-list?questionStartId&status=\"all\">; rel=\"allQuestionList\","
						+ "</admin/report/comment>; 								rel=\"commentReportList\","
						+ "</admin/report/article>; 								rel=\"articleReportList\","
						+ "</admin/reporters>;  									rel=\"reporterList\","
						+ "</admin/article/waiting>;  							rel=\"waitingArticleList\",");

		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 204
		
	}
}
