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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			AdminProcedureService adminProcedureService, Environment env) {
		this.env = env;
		this.mailService = mailService;
		this.statisticsService = statisticsService;
		this.adminProcedureService = adminProcedureService;

	}

	/**
	 * 기자 목록 
	 * 날짜 : 2021-01-07 
	 * 작성자 : 이종현
	 */
	@GetMapping(path = "/reporters")
	public ResponseEntity<List<Map<String, Object>>> getReportersList() {
		List<Map<String, Object>> list = null;
		Map<String, Object> params = null;
		try {
			params = new HashMap<String, Object>();
			params.put("_switch", "admin_reporters_list");
			list = adminProcedureService.execuAdminProcedureList(params);
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
	 */
	@PostMapping(path = "/reporters")
	public ResponseEntity<UserDto> createReporter(@RequestBody Map<String, Object> body) throws ParseException {
		String email = (String) body.get("email");
		String pwd = (String) body.get("pwd");
		String auth = "reporter";
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "create_reporter");
		params.put("_email", email);
		params.put("_pwd", pwd);
		params.put("_auth", auth);
		String nickName = (String) body.get("nickname");
		String name = (String) body.get("name");
		String local = (String) body.get("local");

		String Stringbirth = (String) body.get("birth");
		SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
		java.sql.Date birth = java.sql.Date.valueOf(Stringbirth);

		String gender = (String) body.get("gender");
		String image = (String) body.get("image");
		params.put("_nickName", nickName);
		params.put("_name", name);
		params.put("_local", local);
		params.put("_birth", birth);
		params.put("_gender", gender);
		params.put("_image", image);

		adminProcedureService.execuAdminProcedure(params);
		if (params.get("result_set").equals("conflict")) {
			return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
		}

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
	
	/**
	 * 댓글 블라인드 토글
	 * 작성자 : 이종현 
	 * 작성일 :2021-01-11
	 */
	@PatchMapping(path = "/report/comment")
	public ResponseEntity<Map<String, Object>> toggleReport(
			@RequestParam("commentId") int commentId, @RequestParam("delFlag") String delFlag){
		Map<String, Object> params = null;
		try {
			params = new HashMap<String, Object>();
			params.put("_switch","comment_report_toggle");
			params.put("_commentId", commentId);
			params.put("_delFlag", delFlag);
			adminProcedureService.execuAdminProcedure(params);
			if(params.get("result_set").equals("404")) {
				return new ResponseEntity<Map<String,Object>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);
	}
	
	/**
	 * 댓글 신고 확인
	 * 작성자 : 이종현 
	 * 작성일 :2021-01-11
	 */
	@GetMapping(path = "/report/comment/done")
	public ResponseEntity<Map<String, Object>> reportCheck(@RequestParam("commentReportId") int commentReportId){
		Map<String, Object> params = null;
		try {
			params = new HashMap<String, Object>();
			params.put("_switch","comment_report_check");
			params.put("_commentReportId", commentReportId);
			adminProcedureService.execuAdminProcedure(params);
			if(params.get("result_set").equals("404")) {
				return new ResponseEntity<Map<String,Object>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(HttpStatus.OK);
	}
	
	/**
	 * 신고 댓글 목록
	 * 작성자 : 이종현 
	 * 작성일 :2021-01-11
	 */
	@GetMapping(path = "/report/comment")
	public ResponseEntity<List<Map<String, Object>>> reportList(
			@RequestParam("commentStartId") int commentStartId, @RequestParam("doneFlag") String doneFlag){
		List<Map<String, Object>> list = null;
		List<Map<String, Object>> listTemp = null;
		Map<String, Object> params = null;
		Map<String, Object> map = null;
		try {
			list = new ArrayList<Map<String,Object>>();
			listTemp = new ArrayList<Map<String,Object>>();
			params = new HashMap<String, Object>();
			params.put("_switch","comment_report_list");
			params.put("_commentId", commentStartId);
			params.put("_doneFlag", doneFlag);
			list = adminProcedureService.execuAdminProcedureList(params);
			
			for(int i=0; i<list.size(); i++) {
				map = new HashMap<String, Object>();
				map.put("id", list.get(i).get("id"));
				map.put("reason", list.get(i).get("reason"));
				map.put("createdAt", list.get(i).get("createdAt"));
				map.put("doneFlag", list.get(i).get("doneFlag"));				
				
				params = new HashMap<String, Object>();
				params.put("id", list.get(i).get("userId"));
				params.put("email", list.get(i).get("email"));
				map.put("user", params);
				
				params = new HashMap<String, Object>();
				params.put("id", list.get(i).get("commentId"));
				params.put("content", list.get(i).get("content"));
				params.put("delFlag", list.get(i).get("delFlag"));
				
				map.put("comment", params);
				listTemp.add(map);	
			}
			
			list = new ArrayList<Map<String,Object>>();
			map = new HashMap<String, Object>();
			
			params = new HashMap<String, Object>();
			params.put("rel", "blindComment");
			params.put("href", "admin/report/comment="+commentStartId+"&delFlag=T");
			params.put("method", "patch");
			list.add(params);
			
			params = new HashMap<String, Object>();
			params.put("rel", "blindComment");
			params.put("href", "admin/report/comment="+commentStartId+"&delFlag=F");
			params.put("method", "patch");
			list.add(params);
			
			map.put("_links", list);
			listTemp.add(map);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String, Object>>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Map<String, Object>>>(listTemp,HttpStatus.OK);
	}
	/**
	 * 대기중 기사 상세
	 * 작성자 : 이종현 
	 * 작성일 :2021-01-13
	 */
	@GetMapping(path = "/article/detail")
	public ResponseEntity<Map<String, Object>> getArticleDetail(
			@RequestParam("articleId") int articleId, @RequestParam("status") String status, HttpServletResponse response){
		List<Map<String, Object>> list = null;
		Map<String, Object> params = null;
		Map<String, Object> map = null;
		Map<String, Object> temp = null;
		Map<String, Object> mapAll = null;
		try {
			params = new HashMap<String, Object>();
			temp = new HashMap<String, Object>();
			mapAll = new HashMap<String, Object>();
			params.put("_switch", "article_wait_detail");
			params.put("_id", articleId);
			params.put("_status", status);
			map = adminProcedureService.execuAdminProcedureMap(params);
			params.put("_switch", "article_wait_detail_image");
			list = adminProcedureService.execuAdminProcedureList(params);
			
			temp.put("id", map.get("id"));
			temp.put("category", map.get("category"));
			temp.put("title", map.get("title"));
			temp.put("subTitle", map.get("sub_title"));
			temp.put("content", map.get("content"));
			temp.put("image", list);
			mapAll.put("article", temp);
			
			temp = new HashMap<String, Object>();
			temp.put("id", map.get("userId"));
			temp.put("name", map.get("name"));
			temp.put("email", map.get("email"));
			mapAll.put("user", temp);
			
			list = new ArrayList<Map<String,Object>>();
			map = new HashMap<String, Object>();
			map.put("rel", "publish");
			map.put("href", "/admin/article="+articleId+"/status=publish");
			map.put("method", "publish");
			list.add(map);
			map = new HashMap<String, Object>();
			map.put("rel", "sendEmailToReporter");
			map.put("href", "/admin/reporters/email");
			map.put("method", "post");
			list.add(map);
			mapAll.put("_links", list);
			
			response.setHeader("links", "</admin/article?status=publish>;"
										+"rel=\"publish\","
										+"</admin/reporters/email>;"
										+"rel=\"sendEmailToReporter\"");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String,Object>>(mapAll,HttpStatus.OK);
	}

}
