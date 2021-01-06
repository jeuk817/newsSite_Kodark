package com.kodark.news.controller.advice;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kodark.news.controller.advice.exceptions.ForbiddenException;
import com.kodark.news.controller.advice.exceptions.UnauthorizedException;

@RestControllerAdvice
public class ExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Map<String, Object>> handleUnauthorizedException(UnauthorizedException e) {
		logger.warn(e.getMessage());
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(ForbiddenException.class)
	protected ResponseEntity<Map<String, Object>> handleForbiddenException(ForbiddenException e) {
		logger.warn(e.getMessage());
		
		return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403
	}
}
