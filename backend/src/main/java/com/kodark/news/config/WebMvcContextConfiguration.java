package com.kodark.news.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.kodark.news.interceptors.JwtInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.kodark.news.controller", "com.kodark.news.interceptors" })
public class WebMvcContextConfiguration implements WebMvcConfigurer {
	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
	private static final String VIEW_RESOLVER_SUFFIX = ".html";
	
	@Autowired
	private JwtInterceptor jwtInterceptor;
    
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/ko/*").setViewName("index");
		registry.addViewController("/en/*").setViewName("index");
	}
	
	@Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
        return viewResolver;
	}

	// 인터셉터 : 요청과 응답을 기록하는 LogInterceptor와 인증을 검사하는 JwtInterceptor를 세팅
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(jwtInterceptor)
			.addPathPatterns("/users")
			.addPathPatterns("/users/*")
			.addPathPatterns("/reporter")
			.addPathPatterns("/reporter/*")
			.addPathPatterns("/admin")
			.addPathPatterns("/admin/*")
			.excludePathPatterns("/users/sign-up");
		
	}
		
}