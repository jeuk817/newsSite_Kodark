//package com.kodark.news.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//import org.springframework.web.servlet.view.JstlView;
//
//
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.kodark.news"})
//public class ApplicationContext implements WebMvcConfigurer {
//	private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/views/";
//	private static final String VIEW_RESOLVER_SUFFIX = ".html"; //vue에 맞게 수정	 
//    
//	@Override
//	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//		configurer.enable();
//		//WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
//	}
//	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
//		registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
//		registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
//		registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
//	}
//	
//	@Override
//	public void addViewControllers(ViewControllerRegistry registry) {
//		// TODO Auto-generated method stub
//		registry.addViewController("/").setViewName("redirect:list");
//	}
//	
//	@Bean
//    public ViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setViewClass(JstlView.class);
//        viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
//        viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
//        return viewResolver;
//	    }
//	@Bean
//	public MultipartResolver multipartResolver() {
//		return null;
//		
//	}
//	
//		
//    
//}
