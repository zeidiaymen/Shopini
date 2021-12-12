package com.example.demo.configuration.produit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProduitPerformanceAspect {
	private static final Logger logger = LogManager.getLogger(ProduitLoggingAspect.class);
	@Around("execution (* com.example.demo.servicesImpl.ProduitServiceImpl.*(..))")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable{
		long start= System.currentTimeMillis();
		Object obj= pjp.proceed();
		long elapsedTime = System.currentTimeMillis() - start;
		logger.info("Produit Method execution time : " + elapsedTime + " milliseconds.");
		return obj;
	}
	
}
