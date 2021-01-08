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
	 * title : 기자에게 이메일보내기 
	 * author : 최윤수 
	 * date :2021-01-07
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
	 * title : 54.기사블라인드 토글
	 * desc :  블라인드처리 on/off
	 * author : 최윤수
	 * date : 2021-01-08
	 * @param : articleId,status	 
	 */
	@PatchMapping(path = "/report/article")
	public ResponseEntity<Map<String, Object>> articleBlind(@RequestBody Map<String, Object> body){
		System.out.println("aaa:"+body);
		Map<String, Object> params = new HashMap<>();
		int articleId = Integer.valueOf((String)body.get("articleId"));
		String status = (String)body.get("status");
		params.put("_id", articleId);
		params.put("_auth", status);
		params.put("_switch", "article_blind");
		adminProcedureService.execuAdminProcedure(params);
		if(params.get("result_set").equals("404")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}else if(params.get("result_set").equals("204")) {
			return new ResponseEntity<>(HttpStatus.RESET_CONTENT);//204
		}else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500		
	}
	/**
	 * title : 신고기사목록
	 * desc : 맵9개사용...
	 * author : 최윤수
	 * date : 2021-01-08
	 * @return : List<Map<String,Object>> 
	 */
	@GetMapping(path = "/report/article")
	public ResponseEntity<List<Map<String, Object>>> articleReportList(){
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> temp = new HashMap<>();
		Map<String, Object> temp1 = new HashMap<>();
		Map<String, Object> temp2 = new HashMap<>();
		Map<String, Object> temp3 = new HashMap<>();
		Map<String, Object> temp4 = new HashMap<>();
		Map<String, Object> temp5 = new HashMap<>();
		Map<String, Object> temp6 = new HashMap<>();
		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> templist = new ArrayList<>();			
		params.put("_switch", "article_list");
		
		try {
			list = adminProcedureService.getArticleList(params);			
			for (int i = 0; i < list.size(); i++) {
				templist = new ArrayList<>();	
				temp = new HashMap<>();
				temp1 = new HashMap<>();
				temp2 = new HashMap<>();
				temp3 = new HashMap<>();
				temp4 = new HashMap<>();
				temp5 = new HashMap<>();
				map = new HashMap<>();
				int id = (int) list.get(i).get("id");				
				int articleId = (int)list.get(i).get("article_id");
				String reason = (String)list.get(i).get("reason");				 				
				map.put("id",id);
				map.put("reason", reason);
				map.put("createdAt", list.get(i).get("created_at"));			
				temp.put("id", list.get(i).get("userId"));
				temp.put("email", list.get(i).get("userEmail"));
				map.put("user", temp);				
				temp1.put("id", list.get(i).get("article_id"));
				temp1.put("title", list.get(i).get("title"));
				temp1.put("status", list.get(i).get("status"));
				map.put("article", temp1);			
				temp2.put("id",list.get(i).get("reporterId"));
				temp2.put("email", list.get(i).get("reporterEmail"));
				map.put("reporter", temp2);			
				temp3.put("rel", "blindArticle");
				temp3.put("href", "\"/admin/report/article?articleId="+articleId+"&status=unpublish\"");
				temp3.put("method", "patch");
				templist.add(temp3);
				temp4.put("rel", "openArticle");
				temp4.put("href", "\"/admin/report/article?articleId="+articleId+"&status=publish\"");
				temp4.put("method", "patch");
				templist.add(temp4);
				temp5.put("rel", "sendEmailToReporter");
				temp5.put("href", "\"/admin/reporters/"+list.get(i).get("reporterEmail"));
				temp5.put("method", "post");
				templist.add(temp5);
				temp6.put("rel", "articleReportDone");
				temp6.put("href", "\"/admin/report/article/done?articleId="+articleId+"\"");
				temp6.put("method", "patch");
				templist.add(temp6);
				map.put("_links",templist);		
				list.set(i, map);
				
			}
			

		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 500
		}
		return new ResponseEntity<List<Map<String, Object>>>(list,HttpStatus.OK);// 200
	}
	
	/**
	 * title : 56.기사신고확인(일단보류 사유 : 이해못함)
	 * desc : 
	 * author : 최윤수
	 * date : 2021-01-07
	 * @param body
	 * @return
	 */
	@PatchMapping(path = "/report/article/done")
	public ResponseEntity<Map<String, Object>> articleReportCheck(@RequestBody Map<String, Object> body){
		Map<String, Object> params = new HashMap<>();
		int articleReportId = Integer.valueOf((String)body.get("articleReportId"));
		params.put("_articleReportId", articleReportId);
		params.put("_switch", "article_report");
		return new ResponseEntity<Map<String,Object>>(HttpStatus.RESET_CONTENT);//205
	}
}