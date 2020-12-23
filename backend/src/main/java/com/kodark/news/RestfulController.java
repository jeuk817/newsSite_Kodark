package com.kodark.news;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.UserDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RestfulController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestfulController.class);
	
	/**
	 * vue file
	 * 2020-12-23
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/*
	 * 이메일인증
	 * 2020-12-23
	 */
	@ResponseBody
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public UserDto auth(String email) {
		return null;
	}
	
	/*
	 * 인증번호 전달 
	 * 2020-12-23
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/verigy", method = RequestMethod.PATCH)
	public UserDto verify(String email, String authString) {
		return null;
	}
	
	/*
	 * 회원가입 
	 */
	@ResponseBody
	@RequestMapping(value = "/users/sign-up", method = RequestMethod.POST)
	public UserDto signUp(String id, String pwd) {
		return null;
	}
	
	/*
	 * 로그인
	 * 2020-12-23
	 */
	@ResponseBody
	@RequestMapping(value = "/auth/sign-in", method = RequestMethod.POST)
	public UserDto signIn(UserDto user) {
		return null;
	}
	/*
	 * 회원정보
	 * 2020-12-23
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public UserDto user() {
		return null;
	}	
	/*
	 * 메인네비정보
	 * 2020-12-23
	 */
	@RequestMapping(value = "/navigation", method = RequestMethod.GET)
	public void navigation() {
		
	}
	
	
	
	/*
	 * 기자아이디생성,프로필, 기사
	 * 2020-12-23
	 */
}
