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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.Mail;

import com.kodark.news.service.AuthProcedureService;
import com.kodark.news.service.JwtService;
import com.kodark.news.service.MailService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	Environment env;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AuthProcedureService authProcedureService;
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping(path = "/sign-in")
	public ResponseEntity<Map<String, Object>> signIn(@RequestBody Map<String, Object> body, HttpServletResponse response) {
		System.out.println("/sign-in");
		String email = (String)body.get("email");
		String pwd = (String)body.get("pwd");
		
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "sign_in");
		params.put("_email", email);
		authProcedureService.execuAuthProcedure(params);
		
		if(params.get("result_set").equals("success")) {
			if(params.get("_pwd").equals(pwd)) {
				Map<String, Object> claims = new HashMap<>();
				claims.put("id", params.get("_id"));
				claims.put("auth", params.get("_auth"));
				String token = jwtService.createJwt("userInfo", claims, (10 * 1000 * 60));
				
		        Cookie cookie = new Cookie("jwt", token);
		        cookie.setMaxAge(7 * 24 * 60 * 60);
//		        cookie.setSecure(true);
		        cookie.setHttpOnly(true);
		        cookie.setPath("/");
		        
		        response.addCookie(cookie);
		        response.setHeader("Links", "</auth/sign-in>; rel=\"self\", </>; rel=\"next\"");
		        return new ResponseEntity<>(HttpStatus.CREATED); // 201
			}
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 401
	}
	
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
		System.out.println("======================================");
		System.out.println("@PatchMapping");
		System.out.println("======================================");
		String _auth_string = (String) body.get("_auth_string");
		String email = (String)body.get("email");
		Map<String, Object>params = new HashMap<>();
		params.put("_switch", "confirm_verify");
		params.put("_email", email);
		params.put("_auth_string", _auth_string);
		System.out.println(params.toString());
		authProcedureService.execuAuthProcedure(params);
		System.out.println("check:"+params.get("result_set"));
		
		
		if(params.get("result_set").equals("success")) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}else if(params.get("result_set").equals("fail")) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401
//		}else if(params.get("result_set").equals("Request timeout")){
//			return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);//408
		}else {
			System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
	
	}
}

