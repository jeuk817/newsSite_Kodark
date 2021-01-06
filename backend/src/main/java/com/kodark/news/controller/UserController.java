
package com.kodark.news.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.UserDto;
import com.kodark.news.service.AuthProcedureService;
import com.kodark.news.service.MailService;
import com.kodark.news.service.UserService;
import com.kodark.news.service.UsersProceduerService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	Environment env;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthProcedureService authProcedureService;
	
	@Autowired
	UsersProceduerService usersProcedureService;
	
	@GetMapping(path = "/")
	public ResponseEntity<String> userInfo(){
		//????
		return new ResponseEntity<>(HttpStatus.OK);//200 
	}
	
	
	
	//
	@GetMapping(path = "/my-page")
    public ResponseEntity<String> myPage(){	
	    return new ResponseEntity<>(HttpStatus.OK);//200
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

	
	
	@PutMapping(path = "/update")
	public ResponseEntity<String> update(@RequestBody Map<String, Object>body){
		String msg = "update success";
		return new ResponseEntity<>(msg, HttpStatus.OK);//200
	}
	

	@GetMapping(path ="/list")
	public ResponseEntity<List<UserDto>> getInfo(){
		return new ResponseEntity<List<UserDto>>(userService.getInfoUsers(), HttpStatus.OK);//200
	}
	
}

