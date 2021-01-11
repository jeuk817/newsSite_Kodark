package com.kodark.news.interceptors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kodark.news.controller.advice.exceptions.ForbiddenException;
import com.kodark.news.controller.advice.exceptions.UnauthorizedException;
import com.kodark.news.utils.JwtManager;

import io.jsonwebtoken.Claims;

/*
 * title : jwt 검증 인터셉터
 * dec : jwt 검증이 필요한 요청을 가로채서 검증한다.
 * 작성자 : 류제욱
 * 작성일 : 2020-01-06
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Autowired
	JwtManager jwtManager;
	
	// jwt를 검증하고 통과한다면 request attribute에 id를 넣고, 그렇지 않다면 에러를 던진다.
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
		
		Claims claims =  jwtManager.getClaims(jwtCookie);
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
	
	// 요청이 어느 컨트롤러로 가는지 가리기위해 url을 파싱하는 메소드
	private String getControllerToUse(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String urlCommand = requestURI.substring(contextPath.length());
    	String[] urlTokens = urlCommand.split("/");
    	return urlTokens[0]; // admin, reporter, users
	}

}
