package com.kodark.news.controller.advice.exceptions;

import javax.servlet.http.HttpServletRequest;

public class ForbiddenException extends RuntimeException {
	
	public ForbiddenException(HttpServletRequest request) {
		super(ErrorMessage.getForbiddenMessage(request.getRequestURI(), request.getRemoteAddr()));
	}
	
	public ForbiddenException(Exception e) {
		super(e);
	}
}
