//package com.kodark.news.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////	@Override
////	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////		super.configure(auth);
////		auth.inMemoryAuthentication()
////			.withUser("admin").password(encoder().encode("admin")).roles("ADMIN")
////	        .and()
////	        .withUser("user").password(encoder().encode("user")).roles("USER");
////	}
//	
//	@Override
//    public void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
////                .csrf()
////                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//		
//        		.csrf().disable()
//                .httpBasic();
////                .and()
////                .authorizeRequests()
////                .antMatchers("/db/**").hasRole("ADMIN");
//    }
//	
////	@Bean
////    public PasswordEncoder encoder() {
////        return new BCryptPasswordEncoder();
////    }
//}
