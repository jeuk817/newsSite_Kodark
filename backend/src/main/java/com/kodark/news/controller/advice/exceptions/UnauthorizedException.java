package com.kodark.news.controller.advice.exceptions;

import javax.servlet.http.HttpServletRequest;

public class UnauthorizedException extends RuntimeException {
	
	public UnauthorizedException(HttpServletRequest request) {
		super(ErrorMessage.getUnauthorizedMessage(request.getRequestURI(), request.getRemoteAddr()));
	}
	
	public UnauthorizedException(Exception e) {
		super(e);
	}
}
