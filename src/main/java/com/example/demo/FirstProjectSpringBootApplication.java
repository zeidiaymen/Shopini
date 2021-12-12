package com.example.demo;


import java.io.IOException;




import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.services.produit.IImagesProduitService;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import springfox.documentation.swagger2.annotations.EnableSwagger2;




@EnableSwagger2
@EnableScheduling
@SpringBootApplication
@ComponentScan()
@EnableAutoConfiguration
@EnableAspectJAutoProxy
public class FirstProjectSpringBootApplication {
	@Autowired
	IImagesProduitService storageService;
	
	public static void main(String[] args) throws MessagingException, TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		SpringApplication.run(FirstProjectSpringBootApplication.class, args);

	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		storageService.init();
	}
	

	

	

	

}
