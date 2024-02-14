package com.william.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))") //acá se define cuándo se intercepta y qué clases //"*" cualquier tipo de retorno //"(..)" todos los argumentos //GreetingService.* todas las clases o GreetingService.sayHello a una clase específica y lo mismo se hace un nivel anterior para que aplique a todos los packages
    public void loggerBefore(JoinPoint jointPoint){

        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());    //obtiene los argumentos de los métodos y los convierte a String para poder mostrarlos
        logger.info("Antes: "+ method + " con los argumentos "+args);
    }

    @After("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))") //acá se define cuándo se intercepta y qué clases //"*" cualquier tipo de retorno //"(..)" todos los argumentos //GreetingService.* todas las clases o GreetingService.sayHello a una clase específica y lo mismo se hace un nivel anterior para que aplique a todos los packages
    public void loggerAfter(JoinPoint jointPoint){

        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());    //obtiene los argumentos de los métodos y los convierte a String para poder mostrarlos
        logger.info("Después: "+ method + " con los argumentos "+args);
    }
}
