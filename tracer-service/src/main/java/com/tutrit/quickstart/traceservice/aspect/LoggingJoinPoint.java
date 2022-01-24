package com.tutrit.quickstart.traceservice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingJoinPoint {

    @Pointcut("execution(* com.tutrit..*.*(..))")
    public void tutritMethodExecution() {
    }
}
