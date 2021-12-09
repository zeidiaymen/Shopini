package com.example.demo;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class FirstProjectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstProjectSpringBootApplication.class, args);
	}

     
     
  
	
}
