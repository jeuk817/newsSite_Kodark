package com.kodark.news.controller;

<<<<<<< HEAD
=======
import java.util.HashMap;
<<<<<<< HEAD
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
import java.util.List;
=======
>>>>>>> 7a50b4c9d0ff0a4dc2b9aace164a0d9284f5b9cc
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.Mail;
<<<<<<< HEAD
import com.kodark.news.dto.TestDto;
<<<<<<< HEAD
=======
=======
>>>>>>> 7a50b4c9d0ff0a4dc2b9aace164a0d9284f5b9cc
import com.kodark.news.service.AuthProcedureService;
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
import com.kodark.news.service.MailService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
	
	@Autowired
	Environment env;
	
	@Autowired
	MailService mailService;
	
	@Autowired
<<<<<<< HEAD
	TestService testService;
=======
	AuthProcedureService authProcedureService;
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
	
	@PostMapping
	public ResponseEntity<String> auth(@RequestBody Map<String, Object> body) {
<<<<<<< HEAD
		String email = (String) body.get("email");
		System.out.println(email);
<<<<<<< HEAD
		List<TestDto> allTests = testService.getAllTests();
//		TestDto test = testService.getTest(3);
		System.out.println(allTests);
//		System.out.println(test);
//		System.out.println(env.getProperty("email.username"));
//		Mail mail = new Mail();
//		mail.setMailFrom(env.getProperty("email.username"));
//		mail.setMailTo(email);
//		mail.setMailSubject("Test mail subject");
//		mail.setMailContent("test mail content. <p>hi</p><h1>hi2</h1>");
//		
//		mailService.sendMail(mail);
		
		return new ResponseEntity<>(HttpStatus.CREATED); // 201
=======
		Map<String, Object> params = new HashMap<>();
=======
		String email = (String) body.get("email");		
		Map<String, Object> params = new HashMap<>();		
>>>>>>> 7a50b4c9d0ff0a4dc2b9aace164a0d9284f5b9cc
		params.put("_switch", "create_auth");
		params.put("_email", email);		
		authProcedureService.execuAuthProcedure(params);
		System.out.println("params:"+params);

		
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
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
	}
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
