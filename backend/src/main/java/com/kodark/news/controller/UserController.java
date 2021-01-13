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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	 * 대댓글 
	 * 작성 날짜 : 2021-01-08 
	 * 작성자 : 이종현
	 */
	@PostMapping(path = "/comment/reply")
	public ResponseEntity<String> writeCommentReply(@RequestParam("commentId") int commentId,
			@RequestBody Map<String, Object> body) {
		Map<String, Object> params = null;
		try {
			params = new HashMap<String, Object>();
			params.put("_commentId", commentId);
			params.put("_email", body.get("email"));
			params.put("_content", body.get("content"));
			usersProcedureService.writeCommentReply(params);

		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	/**
	 * title : 37.구독취소
	 * desc : 구독취소
	 * author : 최윤수
	 * date : 2021-01-11
	 * @param : reporterId
	 */
	@DeleteMapping(path = "/subscription")
	public ResponseEntity<String> cancelSubscription(@RequestParam int reporterId,HttpServletRequest request){
		Map<String, Object> params = new HashMap<>();		
		int id = 4;	//request.getAttribute("id");
		params.put("_switch", "cancel_sub");
		params.put("_id", id);
		params.put("_reporter_id", reporterId);
		try {
			usersProcedureService.execuUsersProcedure(params);
			System.out.println("params:"+params);
			if(params.get("result_set").equals("404")) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}		
		return new ResponseEntity<>(HttpStatus.RESET_CONTENT);//205
	}
	/**
	 * title : 36.newsletter toggle
	 * desc :
	 * author : 최윤수
	 * date : 2021-01-11
	 * @param : reporterId
	 */
	@PatchMapping(path = "/reporters/letter-accepted")
	public ResponseEntity<String> newsletterToggle(@RequestParam int reporterId,HttpServletRequest request){
		Map<String, Object> params = new HashMap<>();	
		int id = 5;	//request.getAttribute("id");
		params.put("_switch", "toggle");
		params.put("_id", id);
		params.put("_reporter_id", reporterId);
		try {
			usersProcedureService.execuUsersProcedure(params);
			if(params.get("result_set").equals("404")) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
	}
	
	/**
	 * title : 35.구독목록
	 * desc : 
	 * author : 최윤수
	 * date : 2021-01-11
	 * 
	 */
	@GetMapping(path = "/my-page/subscribed-list")
	public ResponseEntity<List<Map<String,Object>>> subscribeList(HttpServletResponse response,HttpServletRequest request){
		int id = 6;	//request.getAttribute("id);
		List<Map<String,Object>>list = new ArrayList<>();
		List<Map<String,Object>>link;
		Map<String, Object> params = new HashMap<>();
		Map<String, Object> map1;
		Map<String, Object> map2;	
		Map<String, Object> map3;	
		Map<String, Object> map4;
		params.put("_switch", "sub_list");
		params.put("_id", id);				
		list = usersProcedureService.subList(params);
		if(params.get("result_set").equals("404")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}else if(params.get("result_set").equals("200")){
			try {
				for(int i=0;i<list.size();i++) {
					link = new ArrayList<>();
					map1 = new HashMap<>();
					map2 = new HashMap<>();
					map3 = new HashMap<>();	
					map4 = new HashMap<>();
					map1.put("id", list.get(i).get("reporter_id"));
					map1.put("email", list.get(i).get("email"));
					map1.put("nickName", list.get(i).get("nick_name"));
					map1.put("name", list.get(i).get("name"));
					map1.put("image", list.get(i).get("image"));
					map1.put("letterAccepted", list.get(i).get("letter_accepted"));
					map2.put("rel", "reporterArticles");
					map2.put("href", "/users/reporter/article");
					map2.put("method", "get");
					map3.put("rel", "letterAccept");
					map3.put("href", "/users/reporters/letter-accepted");
					map3.put("method", "patch");
					map4.put("rel", "deleteSubscription");
					map4.put("href", "/users/subscription");
					map4.put("method", "delete");
					link.add(map2);
					link.add(map3);
					link.add(map4);
					map1.put("_links", link);
					list.set(i, map1);						
				}
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.OK);//500
			}
		}	
		response.setHeader("links",	  "</users/my-page>; rel=\"myPage\",\r\n"
									+ "</users/my-page/detail>; rel=\"userDetail\",\r\n"
									+ "</users/my-page/subscribed-list>; rel=\"self\",\r\n"
									+ "</users/detail>; rel=\"editUserDetail\",\r\n"
									+ "</users/reporters/article>; rel=\"reporterArticles\",\r\n"
									+ "</users/reporters/letter-accepted>; rel=\"letterAccept\",\r\n"
									+ "</users/subscription>; rel=\"deleteSubscription\"");	
		return new ResponseEntity<List<Map<String,Object>>>(list,HttpStatus.OK);//200
	}
	
	/**
	 * title : 26.기자구독하기
	 * desc : 
	 * author : 최윤수
	 * date :2021-01-12
	 * @param : userId
	 */
	@PostMapping(path = "/subscription")
	public ResponseEntity<String> subscription(@RequestBody Map<String, Object> body, HttpServletRequest request){
		int reporterId =Integer.valueOf((String)body.get("id"));//기자아이디
		int id = 4;	//request.getAttribute("id");
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "subs");
		params.put("_id", id);
		params.put("_reporter_id", reporterId);
		usersProcedureService.execuUsersProcedure(params);
		if(params.get("result_set").equals("409")) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);//409
		}else if(params.get("result_set").equals("201")) {
			return new ResponseEntity<>(HttpStatus.CREATED);//201
		}else
			return new ResponseEntity<>(HttpStatus.CREATED);//500		
	}
}
