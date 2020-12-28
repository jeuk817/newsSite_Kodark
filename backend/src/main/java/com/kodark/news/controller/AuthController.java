package com.kodark.news.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.Mail;
import com.kodark.news.service.MailService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	Environment env;
	
	@Autowired
	MailService mailService;
	
	@PostMapping
	public ResponseEntity<String> auth(@RequestBody Map<String, Object> body) {
		String email = (String) body.get("email");
		System.out.println(email);
		System.out.println(env.getProperty("email.username"));
		Mail mail = new Mail();
		mail.setMailFrom(env.getProperty("email.username"));
		mail.setMailTo(email);
		mail.setMailSubject("Test mail subject");
		mail.setMailContent("test mail content. <p>hi</p><h1>hi2</h1>");
		
		mailService.sendMail(mail);
		
		return new ResponseEntity<>(HttpStatus.CREATED); // 201
	}
}
