package com.tutrit.quickstart.traceservice.aspect;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Configuration
public class LoggingAspect {
    private final Logger log;

    public LoggingAspect(@Value("${trace.log.folder:./tracelog}") String traceLogFolder) {
        final StackTraceElement[] stack = new Throwable().getStackTrace();
        String loggerName = stack[stack.length - 1].getFileName().split("\\.")[0];

        LoggerContext logCtx = (LoggerContext) LoggerFactory.getILoggerFactory();

        PatternLayoutEncoder logEncoder = new PatternLayoutEncoder();
        logEncoder.setContext(logCtx);
        logEncoder.setPattern("%-13date{YYYY-MM-dd HH:mm:ss.SSS}  %-5level [%-24thread] %-32logger : %msg%n");
        logEncoder.start();

        RollingFileAppender logFileAppender = new RollingFileAppender();
        logFileAppender.setContext(logCtx);
        logFileAppender.setName("logFile");
        logFileAppender.setEncoder(logEncoder);
        logFileAppender.setAppend(true);
        logFileAppender.setFile(traceLogFolder + "/trace.log");

        TimeBasedRollingPolicy logFilePolicy = new TimeBasedRollingPolicy();
        logFilePolicy.setContext(logCtx);
        logFilePolicy.setParent(logFileAppender);
        logFilePolicy.setFileNamePattern(traceLogFolder + "logs/trace-%d{yyyy-MM-dd_HH}.log");
        logFilePolicy.setMaxHistory(7);
        logFilePolicy.start();

        logFileAppender.setRollingPolicy(logFilePolicy);
        logFileAppender.start();

        Logger logger = logCtx.getLogger(loggerName);
        logger.setAdditive(false);
        logger.setLevel(Level.INFO);
        logger.addAppender(logFileAppender);
        log = logger;
    }

    @Before("LoggingJoinPoint.tutritMethodExecution())")
    public void logBefore(JoinPoint jp) {
        log.info("--> {}", jp.getSignature());
        logObject(jp.getArgs());
    }

    @AfterReturning(pointcut = "LoggingJoinPoint.tutritMethodExecution())", returning = "returned")
    public Object logAfterReturning(JoinPoint joinPoint, Object returned) {
        log.info("<-- {}", joinPoint.getSignature());
        logObject(returned, true);
        return returned;
    }

    @AfterThrowing(pointcut = "LoggingJoinPoint.tutritMethodExecution())", throwing = "ex")
    public void logAfterThrowing(JoinPoint jp, Throwable ex) {
        log.info("<-- {} THREW {}: {}", jp.getSignature(), ex.getClass().getSimpleName(), ex.getMessage());
    }

    private void logObject(Object[] objs) {
        Arrays.stream(objs).forEach(o -> logObject(o, false));
    }

    private void logObject(Object obj, boolean isReturnedObj) {
        Optional.ofNullable(obj).ifPresent(o -> {
            String prefix = "";
            if (isReturnedObj) {
                prefix = "return";
            }
            if (o instanceof Traceable) {
                final Traceable traceable = (Traceable) o;
                log.info("\t\t{} id\t{}\t{}\t{}", prefix, traceable.traceId(), o.getClass().getSimpleName(), o);
            } else {
                log.info("\t\t{} {}\t{}\t{}", prefix, "", o.getClass().getSimpleName(), o);
            }
        });
    }
}
