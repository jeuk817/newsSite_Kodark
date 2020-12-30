package com.kodark.news.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.Mail;
import com.kodark.news.dto.TestDto;
import com.kodark.news.service.MailService;
import com.kodark.news.service.TestService;
import com.kodark.news.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	Environment env;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	UserService userService;
	
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
	public ResponseEntity<String> signUp(@RequestBody Map<String, Object>body){
		String email = (String) body.get("email");
		String pwd = (String)body.get("pwd");		
		userService.insertUser(email,pwd);
		String msg = "success";
		return new ResponseEntity<>(msg, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "update")
	public ResponseEntity<String> update(@RequestBody Map<String, Object>body){
		String msg = "update success";
		return new ResponseEntity<>(msg, HttpStatus.OK);//200
	}
}
