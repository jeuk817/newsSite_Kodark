package com.kodark.news.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kodark.news.dto.AuthStringDto;
import com.kodark.news.dto.UserDto;
import com.kodark.news.service.MailSendService;
import com.kodark.news.service.UserService;

@Controller
@RequestMapping(value = "/auth/*")
public class EmailController {
	@Autowired
	private UserService userservice;
    @Autowired
    private MailSendService mss;

    
	//auth
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public AuthStringDto auth(UserDto dto) {
	 	//임의의 authKey 생성
		AuthStringDto authDto = new AuthStringDto();
		
		//authkey 이메일 발송
		String authKey = mss.sendAuthMail(dto.getEmail());
	    dto.setAuth(authKey);
	
	    return authDto;

	}
	
    
	//authVerify
	@RequestMapping(value = "/auth/verify", method = RequestMethod.PATCH)
	public String authVerify(AuthStringDto authDto ) throws Exception {
		UserDto dto = new UserDto();
		
		//email, authKey 가 일치할경우 authStatus 업데이트
		if(authDto.getAuthString().equals(dto.getAuth()) ) {
			authDto.setConfirm("1");
		    //DB에 authKey 업데이트
			userservice.updateAuthStatus(dto);  
		}else {
			authDto.setConfirm("0");
		}
		

	    userservice.updateAuthStatus(dto);   
	    
	    return "redirect:/home";
	  
	 }

	
	
}
