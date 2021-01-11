package com.kodark.news.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kodark.news.dto.Mail;
import com.kodark.news.dto.UserDto;
import com.kodark.news.service.AdminProcedureService;
import com.kodark.news.service.MailService;
import com.kodark.news.service.StatisticsService;
import com.kodark.news.utils.PasswordEncoderImpl;
import com.kodark.news.utils.Util;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

	Environment env;
	MailService mailService;
	StatisticsService statisticsService;
	AdminProcedureService adminProcedureService;
	Util util;
	PasswordEncoderImpl passwordEncoder;

	@Autowired
	public AdminController(MailService mailService, StatisticsService statisticsService,
			AdminProcedureService adminProcedureService, Environment env, Util util
			, PasswordEncoderImpl passwordEncoder) {
		this.env = env;
		this.mailService = mailService;
		this.statisticsService = statisticsService;
		this.adminProcedureService = adminProcedureService;
		this.util = util;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 기자 목록 
	 * 날짜 : 2021-01-07 
	 * 작성자 : 이종현
	 */
	@GetMapping(path = "/reporters")
	public ResponseEntity<List<Map<String, Object>>> getReportersList() {
		List<Map<String, Object>> list = null;
		try {
			list = adminProcedureService.getReporterList();
		} catch (Exception e) {
			if (list.isEmpty()) {
				return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK);
	}

	/**
	 * 관리자메인 
	 * 작성자 : 최윤수 
	 * 작성일 : 2021-01-06
	 */
	@GetMapping(path = "/statistics")
	public ResponseEntity<Map<String, Object>> mainPage() {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		params.put("_id", 1);
		statisticsService.execuStatisticsProcedure(params);
		list = statisticsService.execuTodayPopularProcedure();
		params.put("todayPopular", list);
		return new ResponseEntity<Map<String, Object>>(params, HttpStatus.OK);// 200
	}

	/**
	 * 발행 대기중 기사 
	 * 작성자 : 이푸름 
	 * 작성일 : 2021-01-05
	 */
	@GetMapping(path = "/article")
	public ResponseEntity<List<Map<String, Object>>> waitingArticle(@RequestParam("status") String status,
			HttpServletResponse response) {
		String _status = status;
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> container;
		Map<String, Object> reporter;
		Map<String, Object> article;
		Map<String, Object> link;
		list = adminProcedureService.getWaitArticles(_status);

		for (int i = 0; i < list.size(); i++) {
			reporter = new HashMap<>();
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

		response.setHeader("Links", "</admin/article?articleId&status=\"waiting\">; rel=\"waitingArticleDetail\"");
		if (adminProcedureService.getWaitArticles(_status).get(1) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);// 404
		} else if (adminProcedureService.getWaitArticles(_status).get(1) != null) {
			return new ResponseEntity<List<Map<String, Object>>>(list, HttpStatus.OK); // 201;
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 500
		}
	}

	/**
	 * 기자 아이디 생성 
	 * 작성자 : 이푸름 
	 * 작성일 : 2021-01-05
	 * 수정: 류제욱 2021-01-11
	 */
	@PostMapping(path = "/reporters", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<UserDto> createReporter(
			MultipartHttpServletRequest multiRequest, HttpServletRequest request) throws ParseException {
		
		MultipartFile imageFile = multiRequest.getFile("image");
		String fileName = util.saveImage(imageFile, request);
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String encodedPwd = passwordEncoder.encode(pwd);
		String nickName = request.getParameter("nickName");
		String name = request.getParameter("name");
		String local = request.getParameter("local");
		String birth = request.getParameter("birth");
		String gender = request.getParameter("gender");
		
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "create_reporter");
		params.put("_email", email);
		params.put("_pwd", encodedPwd);
		params.put("_nickName", nickName);
		params.put("_name", name);
		params.put("_local", local);
		params.put("_birth", birth);
		params.put("_gender", gender);
		params.put("_image", fileName);
		
		adminProcedureService.createReporter(params);

		return new ResponseEntity<>(HttpStatus.CREATED); // 201
	}

	// 관리자 네비정보
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
	public ResponseEntity<Map<String, Object>> sendMailToReporter(@RequestBody Map<String, Object> body) {
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
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 500
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 204
	}

}