package com.kodark.news.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class UserController {	// restful 기준 나누기
	
	@Autowired
	private UserService userservice;
    @Autowired
    private UserDto dto;
	
    //sign up
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public void signUp(UserDto dto)throws Exception {			
		userservice.signUp(dto);
	}

	//sign in
		@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
		public String login(UserDto dto, HttpServletRequest req, RedirectAttributes rttr) throws Exception{			
			HttpSession session = req.getSession();
			UserDto login = userservice.signIn(dto);		
			if(login == null) {
				session.setAttribute("user", null);
				rttr.addFlashAttribute("msg", false);
			}else {
				session.setAttribute("user", login);
			}
			return "redirect:/";
		}
	
	//sign out
	@RequestMapping(value = "/sign-out", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{		
		session.invalidate();		
		return "redirect:/";
	}
	
	/*
	 * 회원정보
	 * 
	 * 2020-12-23
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public UserDto user(UserDto user) {
		return user;
	}
	
	/*
	 * 회원의 모든 댓글보기
	 */
	@RequestMapping(value = "/commnet", method = RequestMethod.GET)
	public void allComment() {
		
	}
	
	/*
	 * 기자구독하기
	 */
	@RequestMapping(value = "/subscription", method = RequestMethod.POST)
	public UserDto subscription() {
		return null;
	}
	
	/*
	 * 마이페이지
	 */
	@RequestMapping(value = "/my-page", method = RequestMethod.GET)
	public void mypage() {
		
	}
	/*
	 * 회원정보
	 */
	@RequestMapping(value = "/my-page/detail", method = RequestMethod.GET)
	public void mypageDetail() {
		
	}
	/*
	 * 구독목록
	 */
	@RequestMapping(value = "/my-page/subscribe-list", method = RequestMethod.GET)
	public SubscribeDto subscribeList() {
		return null;
	}
	
	/*
	 * 회원정보수정
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.PUT)
	public UserDto detail() {
		return null;
	}
	/*
	 * 비밀번호수정
	 */
	@RequestMapping(value = "/pwd", method = RequestMethod.PATCH)
	public UserDto pwdUpdate() {
		return null;
	}
	
	/*
	 * 회원탈퇴
	 */
	@RequestMapping(value = "/", method = RequestMethod.DELETE)
	public void userDelete() {
		
	}
	
	/*
	 * 뉴스레터토글
	 */
	@RequestMapping(value = "/reporters", method = RequestMethod.PATCH)
	public SubscribeDto toggleNewsLetter() {
		return null;
	}
	
	/*
	 * 구독취소
	 */
	@RequestMapping(value = "/subscription?", method = RequestMethod.DELETE)
	public void cancleSubscribe() {
		
	}
	
}
