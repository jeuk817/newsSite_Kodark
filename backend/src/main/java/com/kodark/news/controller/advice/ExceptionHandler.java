package com.kodark.news.controller.advice;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kodark.news.controller.advice.exceptions.UnauthorizedException;

@RestControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Map<String, Object>> handleUnauthorizedException(UnauthorizedException e) {
		System.out.println(e);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
	}
}
