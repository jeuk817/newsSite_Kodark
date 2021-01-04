package com.kodark.news.service;

import io.jsonwebtoken.Claims;

public interface JwtService {
	String createJwt(String subject, long ttlMillis);
	 
	Claims getClaims(String token);
}
