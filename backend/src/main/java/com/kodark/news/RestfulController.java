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
import com.kodark.news.dto.UserDto;

/**
 * Handles requests for the application home page.
 */
@Controller
public class RestfulController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestfulController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
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
	 * 
	 * 2020-12-23
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String auth(String email) {
		return null;
	}
	/*
	 * 인증번호전달 
	 * 
	 * 2020-12-23
	 */
	@RequestMapping(value = "/auth/verify", method = RequestMethod.PATCH)
	public String verify(String email, String authString) {
		 return null;
	 }
	
	
	/*
	 * 메인네비정보
	 * 
	 * 2020-12-23
	 */
	@RequestMapping(value = "/navigation", method = RequestMethod.GET)
	public void navigation() {
		
	}
	
	/*
	 * 구독기자의 기사목록
	 */
	@RequestMapping(value = "/reporters/article?", method = RequestMethod.GET)
	public UserDto showArticleList() {
		return null;
	}
	
	/*
	 * 고객센터
	 */
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String  help() {
		return "/help";
	}
	
	/*
	 * 내문의글
	 */
	@RequestMapping(value = "/help/question-list", method = RequestMethod.GET)
	public void questionList() {
		
	}
	
	/*
	 * 문의글 상세페이지
	 */
	@RequestMapping(value = "/help/question?", method = RequestMethod.GET)
	public void questionDetail() {
		
	}
	
	/*
	 * 문의글작성
	 */
	@RequestMapping(value = "/help/question", method = RequestMethod.POST)
	public QuestionDto question() {
		return null;
	}


}
