package com.kodark.news.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kodark.news.controller.advice.exceptions.UnauthorizedException;
import com.kodark.news.service.JwtService;

import io.jsonwebtoken.Claims;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	JwtService jwtService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("JwtInterceptor");
		String jwtCookie = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies == null) throw new UnauthorizedException(request); // 401
		
		for(int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("jwt")) {
				jwtCookie = cookies[i].getValue();
				break;
			}
		}
		
		if(jwtCookie == null) throw new UnauthorizedException(request); // 401
		
		Claims claims =  jwtService.getClaims(jwtCookie);
		request.setAttribute("id", claims.get("id"));
		String controllerToUse = getControllerToUse(request);
		
		if(claims.get("auth").equals("admin")) return true; // admin
		
		if(claims.get("auth").equals("reporter")) { // reporter
			if(controllerToUse.equals("admin")) return false; // forbbiden
			else return true;
		}
		
		if(claims.get("auth").equals("user")) { // user
			if(controllerToUse.equals("admin") || controllerToUse.equals("reporter")) return false; // forbbiden
			else return true;
		}
		
		return false; // wrong jwt forbbiden
	}
	
	private String getControllerToUse(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlCommand = requestURI.substring(contextPath.length());
    	String[] urlTokens = urlCommand.split("/");
    	return urlTokens[0]; // admin, reporter, users
	}

}
