package com.example.trafficpoliceman.logging.config;

import com.example.trafficpoliceman.logging.aspect.LoggingAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Aspect
@Slf4j
@Configuration
@ConditionalOnProperty (prefix = "logger", value = "enabled", havingValue = "false", matchIfMissing = true)
public class ConsoleLoggerConfig {

    @Bean
    public LoggingAspect loggingAspect(){
        return new LoggingAspect();
    }

    @Before(value = "com.example.trafficpoliceman.logging.aspect.LoggingAspect.toLogController()")
    public void beforeController(JoinPoint joinPoint){
        log.info("Get request \n Method used: {}", joinPoint.getSignature());
    }


    @Before( value = "com.example.trafficpoliceman.logging.aspect.LoggingAspect.toLogAllMethods()")
    public void beforeMethodExecution(JoinPoint joinPoint){
        log.info("method [{}] call args: [{}]", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(value = "com.example.trafficpoliceman.logging.aspect.LoggingAspect.toLogAllMethods()", returning = "result")
    public void afterMethodExecution(JoinPoint joinPoint, Object result){
        log.info("method [{}] result: [{}]", joinPoint.getSignature(), result);
    }


    @AfterThrowing (value = "com.example.trafficpoliceman.logging.aspect.LoggingAspect.toLogAllMethods()", throwing = "exception")
    public void throwingLog(JoinPoint joinPoint, Throwable exception){
        log.error("method [{}] throw exception", joinPoint.getSignature(),exception);
    }
}