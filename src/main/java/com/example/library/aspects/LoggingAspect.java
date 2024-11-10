package com.example.library.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Pointcut for all methods in the service package
    @Pointcut("execution(* com.example.library.service.*.*(..))")
    public void serviceMethods() {}

    // Log method calls before execution
    @Before("serviceMethods()")
    public void logBeforeMethodCall() {
        logger.info("Method called...");
    }

    // Log method return after execution
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterMethodCall(Object result) {
        logger.info("Method executed successfully, returned: {}", result);
    }

    // Log exceptions thrown by methods
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterThrowingException(Exception ex) {
        logger.error("An exception occurred: {}", ex.getMessage());
    }

    // Measure execution time with @Around advice
    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            logger.error("Exception in {} with cause = {}", joinPoint.getSignature(), ex.getMessage());
            throw ex;
        }
        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Execution time of {} is {} ms", joinPoint.getSignature(), timeTaken);
        return result;
    }
}
