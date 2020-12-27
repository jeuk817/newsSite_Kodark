package com.kodark.news.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.zip.DataFormatException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.kodark.news.dto.QuestionDto;
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
	@RequestMapping(value = "/ko/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "redirect:/ko/home";
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
	
	@ExceptionHandler(DataFormatException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "page do not found")
	public void dataFormatException() {
		
	}

}
