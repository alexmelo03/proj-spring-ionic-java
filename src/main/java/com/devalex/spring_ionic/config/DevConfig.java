package com.devalex.spring_ionic.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.devalex.spring_ionic.services.DBService;
import com.devalex.spring_ionic.services.EmailService;
import com.devalex.spring_ionic.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
		
		if (!"create".equals(strategy)) {
			return false; 
		}
		dbService.instantiateDataBase();
		
		return true;
		
	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}

}
