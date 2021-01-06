package com.kodark.news.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.controller.advice.exceptions.UnauthorizedException;
import com.kodark.news.dto.Mail;

import com.kodark.news.service.AuthProcedureService;
import com.kodark.news.service.MailService;
import com.kodark.news.utils.JwtManager;
import com.kodark.news.utils.PasswordEncoderImpl;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	Environment env;
	MailService mailService;
	AuthProcedureService authProcedureService;
	JwtManager jwtManager;
	PasswordEncoderImpl passwordEncoder;
	
	@Autowired
	public AuthController(Environment env, MailService mailService
			, AuthProcedureService authProcedureService, JwtManager jwtManager
			, PasswordEncoderImpl passwordEncoder) {
		this.env = env;
		this.mailService = mailService;
		this.authProcedureService = authProcedureService;
		this.jwtManager = jwtManager;
		this.passwordEncoder = passwordEncoder;
	}
	
	/*
	 * title : 인증번호 요청
	 * dec : 요청한 이메일이 가입된 이메일인지 확인하고, 그렇지 않다면 인증번호를 보낸다.
	 * 작성자 : 류제욱
	 * 작성일 : 2020-01-06
	 */
	@PostMapping
	public ResponseEntity<String> auth(@RequestBody Map<String, Object> body) {

		String email = (String) body.get("email");		
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "create_auth");
		params.put("_email", email);
		authProcedureService.execuAuthProcedure(params);

		
		if(params.get("result_set").equals("success")) {
			Mail mail = new Mail();
			mail.setMailFrom(env.getProperty("email.username"));
			mail.setMailTo((String)params.get("_email"));
			mail.setMailSubject("The Kodark Times's verification code");
			mail.setMailContent("<h1>Kodark Times's verification code</h1><span>code :</span><span>"
					+ (String)params.get("_auth_string") + "</span>");
			
			mailService.sendMail(mail);
			
			return new ResponseEntity<>(HttpStatus.CREATED); // 201
		} else if(params.get("result_set").equals("conflict")) {
			return new ResponseEntity<>(HttpStatus.CONFLICT); // 409			
		} else if(params.get("result_set").equals("not content")) {
			return new ResponseEntity<>(HttpStatus. NO_CONTENT); // 409			
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500
		}
	}

	@PatchMapping(path="/verify")
	public ResponseEntity<String> verify(@RequestBody Map<String, Object> body){
		String authString = (String) body.get("authString");
		String email = (String)body.get("email");
		Map<String, Object>params = new HashMap<>();
		params.put("_switch", "confirm_verify");
		params.put("_email", email);
		params.put("_auth_string", authString);
		authProcedureService.execuAuthProcedure(params);
		
		
		if(params.get("result_set").equals("success")) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}else if(params.get("result_set").equals("fail")) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401
		}else if(params.get("result_set").equals("expiration")){
			return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);//408
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	
	}
	
	/*
	 * title : 로그인
	 * dec : 가입된 이메일인지 확인하고, 비밀번호를 비교한 후 jwt를 발급한다.
	 * 작성자 : 류제욱
	 * 작성일 : 2020-01-06
	 */
	@PostMapping(path = "/sign-in")
	public ResponseEntity<Map<String, Object>> signIn(@RequestBody Map<String, Object> body
			, HttpServletResponse response) {
		String email = (String)body.get("email");
		String pwd = (String)body.get("pwd");
		
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "sign_in");
		params.put("_email", email);
		authProcedureService.execuAuthProcedure(params);
		
		if(params.get("result_set").equals("success")) {
			String encodedPwd = (String)params.get("_pwd");
			
			if(passwordEncoder.matches(pwd, encodedPwd)) {
				Map<String, Object> claims = new HashMap<>();
				claims.put("id", params.get("_id"));
				claims.put("auth", params.get("_auth"));
				String token = jwtManager.createJwt("userInfo", claims, (10 * 1000 * 60));
				
		        Cookie cookie = new Cookie("jwt", token);
		        cookie.setMaxAge(7 * 24 * 60 * 60);
		        //cookie.setSecure(true);
		        cookie.setHttpOnly(true);
		        cookie.setPath("/");
		        
		        response.addCookie(cookie);
		        response.setHeader("Links", "</auth/sign-in>; rel=\"self\", </>; rel=\"next\"");
		        return new ResponseEntity<>(HttpStatus.CREATED); // 201
			}
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
	}
	
	/*
	 * title : 로그아웃
	 * dec : jwt 쿠키를 제거한다.
	 * 작성자 : 류제욱
	 * 작성일 : 2020-01-06
	 */
	@DeleteMapping(path = "/sign-out")
	public ResponseEntity<Map<String, Object>> signOut(HttpServletResponse response) {
		Cookie cookie = new Cookie("jwt", "");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        
        response.addCookie(cookie);
		return new ResponseEntity<>(HttpStatus.RESET_CONTENT);
	}
	
}

