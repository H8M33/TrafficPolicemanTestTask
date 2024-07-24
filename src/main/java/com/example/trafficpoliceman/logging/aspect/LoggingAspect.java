package com.example.trafficpoliceman.logging.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {


    @Pointcut("within(com.example.trafficpoliceman..*)&& !bean(*Controller) && !execution(* com.example.trafficpoliceman..security..*(..)) ")
    public void toLogAllMethods(){
    }


    @Pointcut("bean(*Controller) && !execution(* com.example.trafficpoliceman..security..*(..))")
    public void toLogController(){
    }
}