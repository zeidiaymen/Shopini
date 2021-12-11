package com.example.demo.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;
@Component


public class AopConfiguration {
	
	
	@Around("execution(* com.example.demo.service.UserService.*(..))")
	public Object userService(ProceedingJoinPoint pjp) throws Throwable {
	long start = System.currentTimeMillis();
	Object obj = pjp.proceed();
	long elapsedTime = System.currentTimeMillis() - start;
	System.out.println("Method execution userService time: " + elapsedTime + " milliseconds.");
	
	return obj;
	}
	
	

}
