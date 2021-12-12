package com.example.demo.configuration.facture;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;





@Component
@Aspect
public class LoggingAop {
	private static final Logger logger = LogManager.getLogger(LoggingAop.class);

	@Before("execution(* com.example.demo.service.facture.*.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	logger.info("In method : " + name );
	}

	@After ("execution(* com.example.demo.service.facture.*.*(..))")
	
	public void logMethodOut (JoinPoint j)
	{
		String name =j.getSignature().getName();
		logger.info("Out method : " + name  );
	}
	
}
