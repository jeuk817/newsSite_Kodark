package com.kodark.news.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.kodark.news.dto.UserDto;
import com.kodark.news.service.AuthProcedureService;
import com.kodark.news.service.MailService;
import com.kodark.news.service.UsersProceduerService;
import com.kodark.news.utils.PasswordEncoderImpl;
import com.kodark.news.utils.Util;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	Environment env;
	MailService mailService;
	AuthProcedureService authProcedureService;
	UsersProceduerService usersProcedureService;
	PasswordEncoderImpl passwordEncoder;
	Util util;

	@Autowired
	public UserController(Environment env, MailService mailService, AuthProcedureService authProcedureService,
			UsersProceduerService usersProcedureService, PasswordEncoderImpl passwordEncoder, Util util) {
		System.out.println("constructor!!!!");
		this.env = env;
		this.mailService = mailService;
		this.authProcedureService = authProcedureService;
		this.usersProcedureService = usersProcedureService;
		this.passwordEncoder = passwordEncoder;
		this.util = util;
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
			String auth = (String)params.get("_auth");
			Map<String, Object> map = new HashMap<>();
			map.put("email", params.get("_email"));
			map.put("auth", auth);
			
			if(auth.equals("user")) {
				response.setHeader("Links",
						"</users/my-page>; rel=\"myPage\""
								+ ", </users/my-page/detail>; rel=\"userDetail\""
								+ ", </users/my-page/subscribed-list>; rel=\"subscribedList\""
								+ ", </users/sign-out>; rel=\"signOut\"");
			} else if(auth.equals("admin")) {
				response.setHeader("Links",
						"</admin/admin-page>; rel=\"adminPage\""
								+ ", </admin/admin-page/users>; rel=\"userManage\""
								+ ", </admin/admin-page/reporters>; rel=\"reporterManage\""
								+ ", </users/sign-out>; rel=\"signOut\"");
			}
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

		response.setHeader("Links", "</users/sign-up>; rel=\"self\"," + "</en/auth/signIn>; rel=\"next\"");
		if (params.get("result_set").equals("success"))
			return new ResponseEntity<>(HttpStatus.CREATED);
		else
			return new ResponseEntity<>(HttpStatus.CONFLICT);

	}
	
	@PatchMapping(path = "/email")
	public ResponseEntity<String> emailUpdate(@RequestBody Map<String, Object> body, HttpServletRequest request) {
		int id = (int)request.getAttribute("id");
		String verifPwd = (String) body.get("verifPwd");
		String email = (String) body.get("email");
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "user_info");
		params.put("_id", id);
		usersProcedureService.execuUsersProcedure(params);
		String encodedPwd = (String) params.get("_pwd");
		
		if(encodedPwd != null && !passwordEncoder.matches(verifPwd, encodedPwd))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
		
		params.put("_switch", "update_email");
		params.put("_id", id);
		params.put("_email", email);
		usersProcedureService.execuUsersProcedure(params);
		String resultSet = (String)params.get("result_set");
		
		if(resultSet.equals("success")) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
		} else if(resultSet.equals("conflict")) {
			return new ResponseEntity<>(HttpStatus.CONFLICT); // 409
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
	}
	

	// 비밀번호 수정
	@PatchMapping(path = "/pwd")
	public ResponseEntity<String> pwdUpdate(@RequestBody Map<String, Object> body, HttpServletRequest request) {
		int id = (int)request.getAttribute("id");
		String pwd = (String) body.get("pwd");
		String verifPwd = (String) body.get("verifPwd");
		
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "user_info");
		params.put("_id", id);
		usersProcedureService.execuUsersProcedure(params);
		String encodedPwd = (String) params.get("_pwd");
		
		if(encodedPwd != null && !passwordEncoder.matches(verifPwd, encodedPwd))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
		
		pwd = passwordEncoder.encode(pwd);
		params.put("_switch", "update_password");
		params.put("_id", id);
		params.put("_pwd", pwd);
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
	 * 회원정보
	 * 작성 날짜 : 2021-01-10
	 * 작성자 : 이푸름
	 */
	
	@GetMapping(path = "/my-page/detail")
	public ResponseEntity<Map<String, Object>> myPageDetail(HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> link1;
		
		int id = (int)request.getAttribute("id");
		params.put("_id", id);

		params.put("_switch", "mypage_detail");
		usersProcedureService.execuUsersProcedure(params);
		
		String NickName= (String) params.get("_nickName");
		String name= (String) params.get("_name");
		String local= (String) params.get("_local");
		String birth = (String) params.get("_birth");
//		Date beforeFormatBirth= (Date) params.get("_birth"); 
//		String pattern = "yyyy-MM-dd";
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
//		String birth = simpleDateFormat.format(beforeFormatBirth);
		
		String gender= (String) params.get("_gender");
		
		Map<String, Object> result = new HashMap<String, Object>();
		link1 = new HashMap<String, Object>();
		link1.put("rel", "editUserDetail");
		link1.put("href", "/users/detail");
		link1.put("method ", "put");
		
		result.put("nickName", NickName);
		result.put("name", name);
		result.put("local", local);
		result.put("birth", birth);
		result.put("gender", gender);
		result.put("_link", link1);
		

		response.setHeader("Links",
						"</users/my-page>; 						rel=\"myPage\","
						+ "</users/my-page/detail>;				rel=\"self\","
						+ "</users/my-page/subscribed-list>;	rel=\"subscribedList\","
						+ "</users/detail>;  					rel=\"editUserDetail\","
						);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);// 200
	}
	
	/**
	 * 회원정보수정 34번
	 * 작성 날짜 : 2021-01-11
	 * 작성자 : 이푸름
	 */
	
	@PutMapping(path = "/detail",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity <UserDto> detailUpdate(
			MultipartHttpServletRequest multiRequest, HttpServletRequest request) {
		System.out.println("=====================================================");
		System.out.println("detailUpdate");
		
		MultipartFile imageFile = multiRequest.getFile("image");
		String fileName = util.saveImage(imageFile, request);
		String name = request.getParameter("name");
		String nickName = request.getParameter("nickName");
		String local = request.getParameter("local");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("_name", name);
		params.put("_nickName", nickName);
		params.put("_local", local);
		params.put("_gender", gender);
		params.put("_birth", birth);
		params.put("_image", fileName);
		
		int id = (int)request.getAttribute("id");
		params.put("_id", id);
		params.put("_switch", "user_update");
		usersProcedureService.execuUsersProcedure(params);
		System.out.println(params);
		if (params.get("result_set").equals("200")) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);// 204
		}else if (params.get("result_set").equals("404")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);// 404
		} else
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 500
	}

}
