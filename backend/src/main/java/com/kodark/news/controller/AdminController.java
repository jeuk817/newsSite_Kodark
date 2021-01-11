package com.kodark.news.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	 * title : 53.신고기사목록
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
	 * title : 49.문의글목록
	 * desc : 문의글 리스트
	 * author : 최윤수
	 * date : 2021-01-10
	 * @param : questionStartId, status
	 * @return : List<Map<String, Object(Map)>>
	 */
	@GetMapping(path = "/question-list") //-넣으면 nullpoint error발생 -대신 /넣으면 잘 실행됨
	public ResponseEntity<List<Map<String,Object>>> questionList(
			@RequestParam(value = "status", required = false,defaultValue = "wait" ) String status, 
			@RequestParam(value = "questionStartId", required = false, defaultValue = "2")int sId,
			HttpServletResponse response
			){
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> temp = new ArrayList<>();
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> maps = new HashMap<>();
		Map<String, Object> userInfo = new HashMap<>();
		 System.out.println(status+":"+sId);
		int id = sId;	
		params.put("_switch","question_list");
		params.put("_id", id-1);		
		try {
			
		
		list = adminProcedureService.getArticleList(params);		
		for(int i=0;i<list.size();i++) {
			System.out.println("list:"+list.get(i));
			maps = new HashMap<>();
			temp = new ArrayList<>();			
			userInfo = new HashMap<>();
			maps.put("id", list.get(i).get("id"));
			maps.put("title",list.get(i).get("title"));
			maps.put("content",list.get(i).get("content"));
			maps.put("answer",list.get(i).get("answer"));
			int userId = (int)list.get(i).get("userId");
			String userEmail = (String)list.get(i).get("userEmail");
			userInfo.put("id", userId);
			userInfo.put("email", userEmail);
			maps.put("user", userInfo);
			temp.add(maps);	
			list.set(i, maps);
			
		}
		
		response.setHeader("Links", "</admin/question-list?questionStartId="+sId+"&status=\"all\">; rel=\"allQuestionList\","
								  + "</admin/question-list?questionStartId="+sId+"&status=\"waiting\">; rel=\"waitingQuestionList\","
								  + "</admin/question-list?questionStartId="+sId+"&status=\"done\">; rel=\"doneQuestionList\",");
		} catch (Exception e) {
			return new ResponseEntity<List<Map<String,Object>>>(list,HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		return new ResponseEntity<List<Map<String,Object>>>(list,HttpStatus.OK);//200
		
	}
	/**
	 * title : 56.기사신고확인(일단보류 사유 : 이해못함)
	 * desc : 
	 * author : 최윤수
	 * date : 2021-01-07
	 * @param : body
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
	
	/**
	 * title : 48. 회원정보 및 이메일 전송
	 * desc : id, 정지사유, 기간(day)을 입력받아 DB에 저장하고 이메일을 발송(forbbiden에 isert되면 users테이블의 status도 변경되게)
	 * author : 최윤수
	 * date : 2021-01-11
	 * @param : id, reason, period 
	 */
	@PostMapping(path = "/users/suspension")
	public ResponseEntity<Map<String,Object>> suspension(@RequestBody Map<String, Object> body){
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		String startDate = fm.format(today);
		Date someday = new Date();
		Map<String,Object> params = new HashMap<>();
		int id = Integer.valueOf((String) body.get("id"));
		String reason = (String) body.get("reason");
		int period = Integer.valueOf((String) body.get("period"));;
		Mail mail = new Mail();
		try {		
			someday.setTime(today.getTime()+((long)period*24*60*60*1000));
			String endDate = fm.format(someday);
			params.put("_id", id);
			params.put("_switch", "suspension");
			params.put("_input", reason);
			adminProcedureService.execuAdminProcedure(params);
			System.out.println("param:"+params);
			mail.setMailFrom(env.getProperty("email.username"));
			String email = (String) params.get("_email");
			System.out.println("email:"+email);
			mail.setMailTo(email);
			mail.setMailSubject("Kodark Times 이용정지안내");
			mail.setMailContent("<h3>reason : </h3>"+reason+"<br><p>Period : "+startDate+" ~ "+endDate+"</p>");
			mailService.sendMail(mail);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 500
		}

		return new ResponseEntity<>(HttpStatus.CREATED);//201
	}
	
	/**
	 * title : 47.회원정보리스트
	 * desc : 관리자메뉴의 회원정보리스트(20개씩나오게)
	 * author : 최윤수
	 * date : 2021-01-11
	 * @param : startIndex
	 */
	@GetMapping(path = "/users")
	public ResponseEntity<List<Map<String,Object>>> userInfo(@RequestParam int startIndex){
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> temp1;
		Map<String, Object> temp2;
		Map<String, Object> temp3;
		List<Map<String, Object>> list = new ArrayList<>();			
		params.put("_switch", "user_info");
		params.put("_id", startIndex-1);
		try {	
			list = adminProcedureService.getArticleList(params);
			for (int i = 0; i < list.size(); i++) {			
				temp1 = new HashMap<>();
				temp2 = new HashMap<>();
				temp3 = new HashMap<>();
				temp1.put("id", list.get(i).get("id"));
				temp1.put("email", list.get(i).get("email"));
				temp1.put("delFlag", list.get(i).get("status"));
				temp2.put("nickName", list.get(i).get("nick_name"));
				temp2.put("name", list.get(i).get("name"));
				temp2.put("local", list.get(i).get("local"));
				temp2.put("birth", list.get(i).get("birth"));
				temp2.put("gender", list.get(i).get("gender"));
				temp2.put("image", list.get(i).get("image"));
				temp1.put("detail", temp2);
				temp3.put("rel", "suspensionUser,");
				temp3.put("href", "'/admin/users/suspension,'");
				temp3.put("method", "post");
				temp1.put("_link", temp3);
				list.set(i, temp1);			
			}		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}		
		return new ResponseEntity<List<Map<String,Object>>>(list,HttpStatus.OK);//200
	}
}