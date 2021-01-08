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

import com.kodark.news.dto.UserDto;
import com.kodark.news.service.AuthProcedureService;
import com.kodark.news.service.MailService;
import com.kodark.news.service.UsersProceduerService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	Environment env;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AuthProcedureService authProcedureService;
	
	@Autowired
	UsersProceduerService usersProcedureService;
	
	//로그인 정보
	@GetMapping(path = "/")
	public ResponseEntity<String> userInfo(){
		return new ResponseEntity<>(HttpStatus.OK);//200 
	}
	
	
	/**
	 * 마이페이지
	 * 작성자 : 최현지
	 * 작성일 : 2021-01-07
	 */
	@GetMapping(path = "/my-page")
    public ResponseEntity<Map<String, Object>> myPage(HttpServletResponse response, HttpServletRequest request){
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> linkList =  new ArrayList<Map<String,Object>>();
		Map<String, Object> link1;
		Map<String, Object> link2;
		Map<String, Object> link3;
		
		request.getAttribute("id");
		int id = 2;
		params.put("_id", id);
		
		params.put("_switch", "my-page");
		usersProcedureService.myPage(params);
		
		String email = (String) params.get("_email");
		String auth = (String) params.get("_auth");
		System.out.println("auth~~~~~~~~~~ " + auth);
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
				"</users/my-page>; 					rel=\"self\","
		  	  + "</users/my-page/detail>;			rel=\"userDetail\","
			  + "</users/my-page/subscribed-list>;	rel=\"subscribedList\","
			  + "</auth>;  							rel=\"eidtEmail\","
			  + "</auth/verify>;  					rel=\"validation\","
			  + "</users>; 							rel=\"deleteUser\"");
		
		return new ResponseEntity<Map<String, Object>>(params, HttpStatus.OK);//200
	}		  
		 
		  

	@PostMapping(path = "/test")
	public ResponseEntity<String> auth(@RequestBody Map<String, Object> body) {
		String email = (String) body.get("email");
		System.out.println(email);
//		List<TestDto> allTests = testService.getAllTests();
//		testService.insertUser();		
//		int id = Integer.valueOf((String)body.get("id"));		
//		testService.deleteUser(id);
//		System.out.println(allTests);

		
		return new ResponseEntity<>(HttpStatus.CREATED); // 201
	}
	
	//회원가입
	@PostMapping(path = "/sign-up")
	public ResponseEntity<String> signUp(@RequestBody Map<String, Object>body, HttpServletResponse response){
		String email = (String) body.get("email");
		String pwd = (String)body.get("pwd");	
		Map<String, Object> params = new HashMap<>();		
		params.put("_switch", "sign_up");
		params.put("_email", email);
		params.put("_pwd", pwd);		
		authProcedureService.execuAuthProcedure(params);
		
		response.setHeader("Links", 
							 "</users/sign-up>; rel=\"self\","
							+"</ko/signIn>; 	rel=\"next\"");
		if(params.get("result_set").equals("success")) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
	}
	
	//로그인
	@PostMapping(path = "/sign-in")
	public ResponseEntity<String> signIn(@RequestBody Map<String, Object>body){
		String email = (String) body.get("email");
		String pwd = (String)body.get("pwd");	
		Map<String, Object> params = new HashMap<>();		
		params.put("_switch", "sign_in");
		params.put("_email", email);
		params.put("_pwd", pwd);		
		authProcedureService.execuAuthProcedure(params);
		
		if(params.get("result_set").equals("no content")) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}else 
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401
	}
//	@DeleteMapping(path = "/sign-out")
//	public ResponseEntity<String> signOut(){		
//		return new ResponseEntity<>(HttpStatus.RESET_CONTENT);//205
//	}
	
	//비밀번호 수정
	@PatchMapping(path = "/pwd")
	public ResponseEntity<String> pwdUpdate(@RequestBody Map<String, Object>body){
		String pwd = (String)body.get("pwd");
		String email = (String) body.get("email");
		int id = Integer.valueOf((String)body.get("id"));
		Map<String, Object> params = new HashMap<>();		
		params.put("_switch", "update_password");		
		params.put("_pwd", pwd);
		params.put("_id", id);
		params.put("_email", email);
		usersProcedureService.execuUsersProcedure(params);
		
		if(params.get("result_set").equals("204")){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}else if(params.get("result_set").equals("401")){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401
		}else if(params.get("result_set").equals("404")){		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);//404
		}else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
	}

	/**
	 * 대댓글 작성
	 * 날짜 : 2021-01-08
	 * 작성자 : 이종현
	 */
	@PostMapping(path ="/comment/reply")
	public ResponseEntity<String> writeCommentReply(@RequestParam("commentId") int commentId, 
												@RequestBody Map<String,Object> body) {
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
	
}
