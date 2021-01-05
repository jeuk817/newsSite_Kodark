package com.kodark.news.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kodark.news.service.JwtService;

import io.jsonwebtoken.Claims;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		return HandlerInterceptor.super.preHandle(request, response, handler);
		System.out.println("JwtInterceptor");
		String jwtCookie = null;
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("jwt")) {
				jwtCookie = cookies[i].getValue();
				break;
			}
		}
		
		if(jwtCookie != null) {
			Claims claims =  jwtService.getClaims(jwtCookie);
			System.out.println(claims.getSubject());
			System.out.println(claims.get("id"));
			System.out.println(claims.get("email"));
			System.out.println(claims.get("auth"));
			request.setAttribute("id", claims.get("id"));
//			request.setAttribute("id", o);
//			return true;
		} else {
			
		}
		return false;
	}

}
