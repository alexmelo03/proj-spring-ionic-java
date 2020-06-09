package com.devalex.spring_ionic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devalex.spring_ionic.services.S3Service;

@SpringBootApplication
public class ProjSpringIonicApplication implements CommandLineRunner {
	
	@Autowired
 	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(ProjSpringIonicApplication.class, args);
	}
	
		
	@Override
	public void run(String... args) throws Exception {	
		s3Service.uploadFile("C:\\temp\\fotos\\alex.jpg");
	
	}

}
