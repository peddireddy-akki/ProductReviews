package com.akki.productreviews.common.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class PerformanceLoggerAspect {

	private static final Logger logger = LogManager.getLogger();

	@Around(value = "execution(* com.akki.productreviews..*.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

		long startTime;
		long endTime;
		long timeTaken;
		Object object;
		startTime = System.currentTimeMillis();
		try {

			object = joinPoint.proceed();
		} catch (Exception exception) {
			endTime = System.currentTimeMillis();
			timeTaken = endTime - startTime;
			logger.debug("Time Taken by {} is {}", joinPoint, timeTaken);

			throw exception;

		}
		endTime = System.currentTimeMillis();
		timeTaken = endTime - startTime;
		logger.debug("Time Taken by {} is {}", joinPoint, timeTaken);

		return object;
	}

}
