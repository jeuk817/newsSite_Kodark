package com.kodark.news.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "com.kodark.news.service", "com.kodark.news.dao" })
@Import({ MailConfig.class, DBConfig.class, MyBatisConfig.class, SecurityConfig.class })
public class ApplicationContextConfig {

}
