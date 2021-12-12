package com.example.demo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.services.produit.IImagesProduitService;

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

	public static void main(String[] args)  {
		SpringApplication.run(FirstProjectSpringBootApplication.class, args);
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		storageService.init();
	}
	/*public void run(String... arg) throws Exception {
	    //storageService.deleteAll();
	    //storageService.init();
	  }*/
	
	 
	@RequestMapping("/")
	String home()
	{
		return "HeyTest";
		
	}
	
}
