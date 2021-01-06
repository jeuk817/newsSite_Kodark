package com.kodark.news.controller.advice.exceptions;

public class ErrorMessage {
	private static final String UNAUTHORIZED = "Uncertified request";
	
	public static String getUnauthorizedMessage(String uri, String ip) {
		return UNAUTHORIZED + " to " + uri + " from " + ip;
	}
}

