package com.kodark.news.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodark.news.service.Utils;

@Controller
public class ServiceController {
	private static final String mydomain = "http%3A%2F%2Flocalhost%3A8080%2Foauth%2Fcallback";
	private static final String clientId = "dCMWFJGFpqXmRgAAAA";
	private static final String clientSecret = "dnHAAAAA";
	private static final String requestUrl = "https://nid.naver.com/oauth2.0/authorize?client_id=" + clientId + "&response_type=code&redirect_uri="+ mydomain + "&state="; 

	@RequestMapping(value = "/naverLogin")
	 public String naverLogin(HttpSession session) {
	  String state = Utils.generateState();    //토큰을 생성합니다.
	  session.setAttribute("state", state);      //세션에 토큰을 저장합니다.
	  return "redirect:" + requestUrl + state;   //만들어진 URL로 인증을 요청합니다.
	}
	
	@RequestMapping("/callback")
	public String callback(@RequestParam String state, @RequestParam String code, HttpServletRequest request) throws UnsupportedEncodingException {		
		String storedState = (String) request.getSession().getAttribute("state");  //세션에 저장된 토큰을 받아옵니다.
		if (!state.equals(storedState)) {             //세션에 저장된 토큰과 인증을 요청해서 받은 토큰이 일치하는지 검증합니다.
		System.out.println("401 unauthorized");   //인증이 실패했을 때의 처리 부분입니다.
	   	return "redirect:/";
	  }	  

	  //AccessToken 요청 및 파싱할 부분
	  return "redirect:/";
	 }
	
	private String getAccessUrl(String state, String code) {
		 String accessUrl = "https://nid.naver.com/oauth2.0/token?client_id=" + clientId + "&client_secret=" + clientSecret
		 + "&grant_type=authorization_code" + "&state=" + state + "&code=" + code;
		 return accessUrl;
		 }
	
	
}
