package com.kodark.news.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Util {
	@Autowired
	JwtManager jwtManager;
	
	public Cookie makeJwtCookie(String key, Map<String, Object> params) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", params.get("_id"));
		claims.put("auth", params.get("_auth"));
		String token = jwtManager.createJwt("userInfo", claims, (10 * 1000 * 60));			
		
        Cookie cookie = new Cookie(key, token);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        //cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        
        return cookie;
	}
}
