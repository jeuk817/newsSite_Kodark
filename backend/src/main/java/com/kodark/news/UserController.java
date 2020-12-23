package com.kodark.news;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kodark.news.dto.SubscribeDto;
import com.kodark.news.dto.UserDto;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	/*
	 * 회원가입
	 * 
	 * 2020-12-23
	 */
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public UserDto signUp(UserDto user) {
		return null;
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
