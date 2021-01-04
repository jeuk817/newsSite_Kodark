package com.kodark.news.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
	String createToken(String subject, long ttlMillis);
	 
//    String getSubject(String token);
	Claims getSubject(String token);
}
