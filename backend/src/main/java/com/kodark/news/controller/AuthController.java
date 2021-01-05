package com.kodark.news.controller;

import java.util.HashMap;
<<<<<<< HEAD
=======
import java.util.List;
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.GetMapping;
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.Mail;
<<<<<<< HEAD
import com.kodark.news.service.AuthProcedureService;
import com.kodark.news.service.MailService;
=======
import com.kodark.news.dto.TestDto;
import com.kodark.news.service.AuthProcedureService;
import com.kodark.news.service.MailService;
import com.kodark.news.service.TestService;
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	Environment env;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	AuthProcedureService authProcedureService;
	
	@PostMapping
	public ResponseEntity<String> auth(@RequestBody Map<String, Object> body) {
<<<<<<< HEAD
		String email = (String) body.get("email");		
		Map<String, Object> params = new HashMap<>();		
		params.put("_switch", "create_auth");
		params.put("_email", email);		
		authProcedureService.execuAuthProcedure(params);
		System.out.println("params:"+params);

=======
		String email = (String) body.get("email");
		System.out.println(email);
		Map<String, Object> params = new HashMap<>();
		params.put("_switch", "create_auth");
		params.put("_email", email);
		authProcedureService.execuAuthProcedure(params);
		System.out.println(params);
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
		
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
<<<<<<< HEAD
		} else if(params.get("result_set").equals("not content")) {
			return new ResponseEntity<>(HttpStatus. NO_CONTENT); // 409			
=======
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500
		}
	}
<<<<<<< HEAD
	@PatchMapping(value = "verify")
	public ResponseEntity<String> verify(@RequestBody Map<String, Object>body){
		String email = (String)body.get("email");
		Map<String, Object>params = new HashMap<>();
		params.put("_switch", "confirm_verify");		
		params.put("_email", email);
		params.put("_auth_string", (String)body.get("authString"));
		System.out.println(params.toString());		
		authProcedureService.execuAuthProcedure(params);
		System.out.println("check:"+params.get("result_set"));
		
		
		
		
		if(params.get("result_set").equals("success")) {
			System.out.println("성공");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);//204
		}else if(params.get("result_set").equals("fail")) {
			System.out.println("실패");
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);//401
		}else if(params.get("result_set").equals("expiration")){
			return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);//408
		}else {
			System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
		}
		
	}
	
}
=======
	
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
//	      }else if(params.get("result_set").equals("Request timeout")){
//	         return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);//408
	      }else {
	         System.out.println("errorrrrrrrrrrrrrrrrrrrrrrr");
	         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);//500
	      }
	      
	   }
	}
>>>>>>> 3e76de9... admin 기자생성, 네비게이션, 발행대기중 기사
