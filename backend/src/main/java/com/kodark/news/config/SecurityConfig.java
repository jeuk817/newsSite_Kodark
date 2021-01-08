package com.kodark.news.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/*
 * title : Security 환경
 * dec : csrf공격을 방어한다.
 * 작성자 : 류제욱
 * 작성일 : 2020-01-06
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
//        		.formLogin().disable()
//                .csrf()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
        		.csrf().disable()
        		.formLogin().disable()
                .httpBasic();
    }
	
}
