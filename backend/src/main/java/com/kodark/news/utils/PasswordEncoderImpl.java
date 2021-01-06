package com.kodark.news.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/*
 * title : 비밀번호 인코더
 * dec : 비밀번호를 인코딩하고, 비교한다.
 * 작성자 : 류제욱
 * 작성일 : 2020-01-06
 */
@Component
public class PasswordEncoderImpl implements PasswordEncoder {
	private PasswordEncoder passwordEncoder;
	
	public PasswordEncoderImpl() {
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	public PasswordEncoderImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	// 비밀번호 인코딩
	@Override
	public String encode(CharSequence rawPassword) {
		return passwordEncoder.encode(rawPassword);
	}
	
	// 비밀번호 비교
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}

}
