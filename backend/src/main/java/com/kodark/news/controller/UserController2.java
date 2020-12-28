package com.kodark.news.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kodark.news.dto.AuthStringDto;
import com.kodark.news.dto.SubscribeDto;
import com.kodark.news.dto.UserDto;
import com.kodark.news.service.MailSendService;
import com.kodark.news.service.UserService;
/**
 * 유저컨트롤러
 * @author ys
 * 2020-12-24
 */
@Controller
@RequestMapping(value = "/users")
public class UserController2 {	// restful 기준 나누기
	
	@Autowired
	private UserService userservice;
    @Autowired
    private UserDto dto;
	
	
	@PostMapping(value ="/sign-up")
	public void signUp(UserDto dto)throws Exception {			
		userservice.signUp(dto);
	}
	
}
