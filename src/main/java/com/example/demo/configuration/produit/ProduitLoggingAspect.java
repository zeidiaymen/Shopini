package com.example.demo.configuration.produit;

import org.aspectj.lang.JoinPoint;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Component
@Aspect
public class ProduitLoggingAspect {
	private static final Logger l =(Logger)  LogManager.getLogger(ProduitLoggingAspect.class);
	@Before("execution(* com.example.demo.servicesImpl.produit.ProduitServiceImpl.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	l.info("Produit In method " + name + " : ");
	}

	@After("execution(* com.example.demo.servicesImpl.produit.ProduitServiceImpl.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		l.info("Produit Out Of method " + name + " : ");
		}
	@Before("execution(* com.example.demo.servicesImpl.produit.DetailProduitServiceImpl.*(..))")
	public void logMethodEntry2(JoinPoint joinPoint) {
	String name = joinPoint.getSignature().getName();
	l.info("Detail Produit In method " + name + " : ");
	}

	@After("execution(* com.example.demo.servicesImpl.produit.DetailProduitServiceImpl.*(..))")
	public void logMethodExit2(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		l.info("Detail Produit Out Of method " + name + " : ");
		}
	
	
}
