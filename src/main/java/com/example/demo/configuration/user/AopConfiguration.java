package com.example.demo.configuration.user;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Component
@Aspect

public class AopConfiguration {
	
	
	@Around("execution(* com.example.demo.service.user.UserService.*(..))")
	public Object userService(ProceedingJoinPoint pjp) throws Throwable {
	long start = System.currentTimeMillis();
	Object obj = pjp.proceed();
	long elapsedTime = System.currentTimeMillis() - start;
	System.out.println("Method execution userService time: " + elapsedTime + " milliseconds.");
	
	return obj;
	}
	
	

	
}
