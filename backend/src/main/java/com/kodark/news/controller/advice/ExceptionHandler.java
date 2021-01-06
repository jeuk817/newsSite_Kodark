package com.kodark.news.controller.advice;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kodark.news.controller.advice.exceptions.ForbiddenException;
import com.kodark.news.controller.advice.exceptions.UnauthorizedException;

/*
 * title : 예외처리 핸들러
 * dec : 실행 중 발생하는 예외를 받아서 처리한다.
 * 작성자 : 류제욱
 * 작성일 : 2020-01-06
 */
@RestControllerAdvice
public class ExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 미인증 예외
	@org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException.class)
	protected ResponseEntity<Map<String, Object>> handleUnauthorizedException(UnauthorizedException e) {
		if(logger.isWarnEnabled())
			logger.warn(e.getMessage());
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
	}
	
	// 접근권한 거부 예외
	@org.springframework.web.bind.annotation.ExceptionHandler(ForbiddenException.class)
	protected ResponseEntity<Map<String, Object>> handleForbiddenException(ForbiddenException e) {
		if(logger.isWarnEnabled())
			logger.warn(e.getMessage());
		
		return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 403
	}
}
