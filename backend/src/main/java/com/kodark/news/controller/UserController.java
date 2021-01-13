package com.kodark.news.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.mappers.UsersProcedureMapper;
import com.kodark.news.service.AuthProcedureService;
import com.kodark.news.service.MailService;
import com.kodark.news.service.UsersProceduerService;
import com.kodark.news.utils.PasswordEncoderImpl;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	Environment env;
	MailService mailService;
	AuthProcedureService authProcedureService;
	UsersProceduerService usersProcedureService;
	PasswordEncoderImpl passwordEncoder;
	
	@Autowired
	public UserController(Environment env, MailService mailService, AuthProcedureService authProcedureService,
			UsersProceduerService usersProcedureService, PasswordEncoderImpl passwordEncoder) {
		this.env = env;
		this.mailService = mailService;
		this.authProcedureService = authProcedureService;
		this.usersProcedureService = usersProcedureService;
		this.passwordEncoder = passwordEncoder;
	}

	@GetMapping
	public ResponseEntity<Map<String, Object>> userInfo(HttpServletRequest request, HttpServletResponse response) {
		int id = (int) request.getAttribute("id");
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "user_info");
		params.put("_id", id);
		usersProcedureService.execuUsersProcedure(params);
		String resultSet = (String) params.get("result_set");
		if (resultSet.equals("success")) {
			Map<String, Object> map = new HashMap<>();
			map.put("email", params.get("_email"));
			map.put("auth", params.get("_auth"));

			response.setHeader("Links",
					"</users/my-page>; rel=\"myPage\"" + ", </users/my-page/detail>; rel=\"userDetail\""
							+ ", </users/my-page/subscribed-list>; rel=\"subscribedList\""
							+ ", </users/sign-out>; rel=\"signOut\"");
			return new ResponseEntity<>(map, HttpStatus.OK);// 200
		} else if (resultSet.equals("not_found")) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}

	/**
	 * 마이페이지 
	 * 작성자 : 최현지 
	 * 작성일 : 2021-01-07
	 */
	@GetMapping(path = "/my-page")
	public ResponseEntity<Map<String, Object>> myPage(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> linkList = new ArrayList<Map<String, Object>>();
		Map<String, Object> link1;
		Map<String, Object> link2;
		Map<String, Object> link3;

		request.getAttribute("id");
		int id = 2;
		params.put("_id", id);

		params.put("_switch", "my_page");
		usersProcedureService.myPage(params);

		String email = (String) params.get("_email");
		String auth = (String) params.get("_auth");
		params.clear();
		
		params.put("email", email);
		params.put("auth", auth);

		link1 = new HashMap<String, Object>();
		link2 = new HashMap<String, Object>();
		link3 = new HashMap<String, Object>();

		link1.put("rel", "deleteUser");
		link1.put("href", "/users");
		link1.put("method ", "delete");
		linkList.add(link1);

		link2.put("rel", "editEmail");
		link2.put("href", "/auth");
		link2.put("method ", "patch");
		linkList.add(link2);

		link3.put("rel", "validation");
		link3.put("href", "/auth/verify");
		link3.put("method ", "patch");
		linkList.add(link3);

		params.put("_links", linkList);

		response.setHeader("Links",
						"</users/my-page>; 						rel=\"self\","
						+ "</users/my-page/detail>;				rel=\"userDetail\","
						+ "</users/my-page/subscribed-list>;	rel=\"subscribedList\","
						+ "</auth>;  							rel=\"eidtEmail\","
						+ "</auth/verify>;  					rel=\"validation\","
						+ "</users>; 							rel=\"deleteUser\"");

		return new ResponseEntity<Map<String, Object>>(params, HttpStatus.OK);// 200
	}

	// 회원가입
	@PostMapping(path = "/sign-up")
	public ResponseEntity<String> signUp(@RequestBody Map<String, Object> body, HttpServletResponse response) {
		String email = (String) body.get("email");
		String pwd = (String) body.get("pwd");
		String encodedPwd = passwordEncoder.encode(pwd);
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "sign_up");
		params.put("_email", email);
		params.put("_pwd", encodedPwd);
		authProcedureService.execuAuthProcedure(params);

		response.setHeader("Links", "</users/sign-up>; rel=\"self\"," + "</ko/auth/signIn>; rel=\"next\"");
		if (params.get("result_set").equals("success"))
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);

	}

	// 비밀번호 수정
	@PatchMapping(path = "/pwd")
	public ResponseEntity<String> pwdUpdate(@RequestBody Map<String, Object> body) {
		String pwd = (String) body.get("pwd");
		String email = (String) body.get("email");
		int id = Integer.valueOf((String) body.get("id"));
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "update_password");
		params.put("_pwd", pwd);
		params.put("_id", id);
		params.put("_email", email);
		usersProcedureService.execuUsersProcedure(params);
		

		if (params.get("result_set").equals("204")) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 204
		} else if (params.get("result_set").equals("401")) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);// 401
		} else if (params.get("result_set").equals("404")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);// 404
		} else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 500
	}
	
	/**
	 * 댓글 입력
	 * 작성 날짜 : 2021-01-12 
	 * 작성자 : 이종현
	 */
	@PostMapping(path = "/comment")
	public ResponseEntity<Map<String, Object>> writeComment(@RequestParam("articleId") int articleId,
			@RequestBody Map<String, Object> body) {
		Map<String, Object> params = null;
		try {
			params = new HashMap<String, Object>();
			params.put("_switch", "comment_write");
			params.put("_id", articleId);
			params.put("_email", body.get("email"));
			params.put("_content", body.get("content"));

			usersProcedureService.execuCommentMapProcedure(params);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(HttpStatus.OK);
	}
	
	/**
	 * 대댓글 입력
	 * 작성 날짜 : 2021-01-08 
	 * 작성자 : 이종현
	 */
	@PostMapping(path = "/comment/reply")
	public ResponseEntity<Map<String, Object>> writeCommentReply(@RequestParam("commentId") int commentId,
			@RequestBody Map<String, Object> body) {
		Map<String, Object> params = null;
		try {
			params = new HashMap<String, Object>();
			params.put("_switch", "comment_reply");
			params.put("_id", commentId);
			params.put("_email", body.get("email"));
			params.put("_content", body.get("content"));

			usersProcedureService.execuCommentMapProcedure(params);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Map<String, Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(HttpStatus.OK);
	}
	/**
	 * 댓글 신고
	 * 설명 : 댓글을 신고한다.
	 * 작성 날짜 : 2021-01-10 
	 * 작성자 : 이종현
	 */
	@PostMapping(path = "/comment/report")
	public ResponseEntity<String> commentReport(
			@RequestParam("commentId") int commentId, @RequestBody Map<String,Object> body){
		Map<String, Object> params = null;
		try {
			
			params = new HashMap<String, Object>();
			params.put("_switch", "comment_report");
			params.put("_id", commentId);
			params.put("_email", body.get("email"));
			params.put("_reason", body.get("reason"));
			usersProcedureService.execuCommentMapProcedure(params);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/**
	 * 댓글 추천/비추천
	 * 설명 : 댓글을 추천/비추천 시 해당 내역을 업데이트 후 해당 댓글의 추천/비추천 수량을 가져온다.
	 * 작성 날짜 : 2021-01-10 
	 * 작성자 : 이종현
	 */
	@PostMapping(path = "/comment/reputation")
	public ResponseEntity<List<Map<String, Object>>> newReputation(
			@RequestParam("commentId") int commentId, @RequestBody Map<String,Object> body){
		List<Map<String, Object>> list = null;
		Map<String, Object> params = null;
		try {
			list = new ArrayList<Map<String,Object>>();
			params = new HashMap<String, Object>();
			params.put("_switch", "comment_reputation");
			params.put("_id", commentId);
			params.put("_email", body.get("email"));
			params.put("_reputation", body.get("reputation"));
		
			list = usersProcedureService.execuCommentListProcedure(params);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String,Object>>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Map<String,Object>>>(list,HttpStatus.OK);
	}
	/**
	 * 특정 회원의 모든 댓글 보기
	 * 설명 : 특정 회원의 Id를 입력 받으면 해당 회원의 모든 댓글 목록을 가져옴
	 * 작성 날짜 : 2021-01-10 
	 * 작성자 : 이종현
	 */
	@GetMapping(path = "/comment")
	public ResponseEntity<Map<String,Object>> testMapper(@RequestParam("userId") int userId){
		List<Map<String,Object>> list = null;
		Map<String, Object> map = null;
		Map<String, Object> temp = null;
		Map<String, Object> params = null;
		try {
			list = new ArrayList<Map<String,Object>>();
			map = new HashMap<String, Object>();
			temp = new HashMap<String, Object>();
			params = new HashMap<String, Object>();
			
			params.put("_switch", "comment_info_id");
			params.put("_userId", userId);
			temp = usersProcedureService.execuCommentMapProcedure(params);
			map.put("id", temp.get("id"));
			map.put("email", temp.get("email"));
			map.put("nickName", temp.get("nick_name"));
			map.put("local", temp.get("local"));
			params = new HashMap<String, Object>();
			params.put("_switch", "comment_info_list");
			params.put("_userId", userId);
			list = usersProcedureService.execuCommentListProcedure(params);
			map.put("comments", list);
			
			
		} catch (Exception e) {
			
		}
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	/**
	 * 감정 표현 선택
	 * 설명 : 기사글의 감정표현을 선택시 articleTB의 값을 업데이트를 하고 업데이트된 결과값을 반환한다.
	 * 작성 날짜 : 2021-01-12
	 * 작성자 : 이종현
	 */
	@PutMapping(path = "/emotion")
	public ResponseEntity<List<Map<String, Object>>> chooseEmotion(
			@RequestParam("articleId") int articleId, @RequestParam("emotion") String emotion){
		List<Map<String, Object>> list = null;
		Map<String, Object> params = null;
		
		try {
			list = new ArrayList<Map<String,Object>>();
			params = new HashMap<String, Object>();
			params.put("_switch","choose_emotion");
			params.put("_id", articleId);
			params.put("_userId", 3); //임시 이메일
			params.put("_emotion", emotion);
			list = usersProcedureService.execuUsersProcedureList(params);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Map<String,Object>>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Map<String,Object>>>(list,HttpStatus.OK);
	}

}