package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ComponentScan()
@EnableAutoConfiguration

public class FirstProjectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstProjectSpringBootApplication.class, args);
	}
	@RequestMapping("/")
	String home()
	{
		return "HeyTest";
		
	}
	
}
