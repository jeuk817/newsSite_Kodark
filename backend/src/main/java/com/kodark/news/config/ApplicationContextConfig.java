package com.kodark.news.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.kodark.news.service" })
@Import({ MailConfig.class })
@PropertySource("classpath:email.properties")
public class ApplicationContextConfig {

}
