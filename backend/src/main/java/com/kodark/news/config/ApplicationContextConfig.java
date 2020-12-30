package com.kodark.news.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.kodark.news.service", "com.kodark.news.dao" })
@Import({ MailConfig.class, DBConfig.class, MyBatisConfig.class })
public class ApplicationContextConfig {

}
