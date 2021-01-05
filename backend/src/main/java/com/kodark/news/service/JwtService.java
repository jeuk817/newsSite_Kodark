package com.kodark.news.service;

import java.util.Map;

import io.jsonwebtoken.Claims;

public interface JwtService {
	String createJwt(String subject, Map<String, Object> claims, long ttlMillis);
	 
	Claims getClaims(String token);
}
