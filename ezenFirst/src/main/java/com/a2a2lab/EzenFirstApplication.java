package com.a2a2lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class EzenFirstApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzenFirstApplication.class, args);
	}

}
