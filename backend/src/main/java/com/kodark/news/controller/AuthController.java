package com.kodark.news.controller;

<<<<<<< HEAD
=======
import java.util.HashMap;
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodark.news.dto.Mail;
import com.kodark.news.dto.TestDto;
<<<<<<< HEAD
=======
import com.kodark.news.service.AuthProcedureService;
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
import com.kodark.news.service.MailService;
import com.kodark.news.service.TestService;

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
		params.put("_switch", "create_auth");
		params.put("_email", email);
		authProcedureService.execuAuthProcedure(params);
		System.out.println(params);
		
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
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); //500
		}
>>>>>>> b100e76c591bfc4de9eb542a5ed80a8e844c8b1b
	}
}
