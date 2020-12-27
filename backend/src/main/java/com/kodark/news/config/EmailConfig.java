package com.kodark.news.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
		@Bean(name="mailSender")
		public JavaMailSender getJavaMailSender() {
			Properties properties = new Properties(); 
			properties.put("email.smtp.auth", true);
			properties.put("email.transport.protocol", "smtp");
			properties.put("email.smtp.starttls.enable", true);
			properties.put("email.smtp.starttls.required", true);
			properties.put("email.debug", true);
			
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost("smtp.gmail.com");
			mailSender.setPort(587);
			mailSender.setUsername("이메일 주소");
			mailSender.setPassword("비밀번호");
			mailSender.setJavaMailProperties(properties);
			
			return mailSender;
			
		}
		
	}
