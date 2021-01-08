package com.kodark.news.controller.advice.exceptions;

public class ErrorMessage {
	private static final String UNAUTHORIZED = "Unauthorized request";
	private static final String FORBIDDEN = "Forbidden request";
	
	public static String getUnauthorizedMessage(String uri, String ip) {
		return UNAUTHORIZED + " to " + uri + " from " + ip;
	}
	
	public static String getForbiddenMessage(String uri, String ip) {
		return FORBIDDEN + " to " + uri + " from " + ip;
	}
}

