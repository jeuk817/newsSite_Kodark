package com.kodark.news.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kodark.news.controller.advice.exceptions.ForbiddenException;
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
		String jwtCookie = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("jwt")) {
					jwtCookie = cookies[i].getValue();
					break;
				}
			}
		}
		
		if(jwtCookie == null) throw new UnauthorizedException(request); // 401
		
		Claims claims =  jwtService.getClaims(jwtCookie);
		request.setAttribute("id", claims.get("id"));
		String controllerToUse = getControllerToUse(request);
		
		// admin
		if(claims.get("auth").equals("admin")) return true;
		
		// reporter
		if(claims.get("auth").equals("reporter")) {
			if(controllerToUse.equals("admin"))
				throw new ForbiddenException(request); // 403
			else return true;
		}
		
		// user
		if(claims.get("auth").equals("user")) {
			if(controllerToUse.equals("admin") || controllerToUse.equals("reporter"))
				throw new ForbiddenException(request); // 403
			else return true;
		}
		
		throw new ForbiddenException(request); // wrong jwt forbbiden
	}
	
	private String getControllerToUse(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlCommand = requestURI.substring(contextPath.length());
    	String[] urlTokens = urlCommand.split("/");
    	return urlTokens[0]; // admin, reporter, users
	}

}
