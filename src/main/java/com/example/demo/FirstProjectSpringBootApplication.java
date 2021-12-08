package com.example.demo;


import java.io.IOException;




import javax.mail.MessagingException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@EnableSwagger2
@SpringBootApplication
@ComponentScan()
@EnableAutoConfiguration

public class FirstProjectSpringBootApplication {
	
	
	public static void main(String[] args) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		SpringApplication.run(FirstProjectSpringBootApplication.class, args);

	}
	
	

	

	

	

}
