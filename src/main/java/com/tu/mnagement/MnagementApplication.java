package com.tu.mnagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class MnagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MnagementApplication.class, args);
		System.out.println("My Application is running.......");
	}

}
