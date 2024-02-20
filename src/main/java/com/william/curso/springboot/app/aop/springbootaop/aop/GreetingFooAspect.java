package com.william.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Aspect
@Component
public class GreetingFooAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Before("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")   //este Before se ejecutará después del Before y Around de GreetingAspect
    @Before("GreetingServicePointcuts.greetingFooLoggerPointCut()")
    public void loggerBefore(JoinPoint joinPoint){

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());
        logger.info("GreetingFooAspect Antes: "+ method + " invocado con los parametros "+ args);
    }
}
