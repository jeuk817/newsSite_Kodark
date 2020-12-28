package com.kodark.news.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	
	@PostMapping
	public ResponseEntity<String> auth(@RequestBody Map<String, Object> body) {
		String email = (String) body.get("email");
		System.out.println(email);
		return new ResponseEntity<>(HttpStatus.CREATED); // 201
	}
}
