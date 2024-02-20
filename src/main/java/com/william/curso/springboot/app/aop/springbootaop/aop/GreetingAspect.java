package com.william.curso.springboot.app.aop.springbootaop.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)   //define el orden de ejecución cuando hay más de una clase Aspect
@Aspect //define la clase como aspecto
@Component  //se define como componenete para que springboot lo pueda reconocer
public class GreetingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Before("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))") //acá se define cuándo se intercepta y qué clases //"*" cualquier tipo de retorno //"(..)" todos los argumentos //GreetingService.* todas las clases o GreetingService.sayHello a una clase específica y lo mismo se hace un nivel anterior para que aplique a todos los packages
    @Before("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerBefore(JoinPoint jointPoint){ //este argumento define que en este método se va a ejecutar un joinPoint que determina dónde se puede aplicar un aspecto. Punto de entrada en la ejecución

        logger.info("=====================loggerBefore=====================");

        String method = jointPoint.getSignature().getName();    //getSignature devuelve la firma de un método: nombre, tipo de retorno y tipos de parámetros que acepta
        String args = Arrays.toString(jointPoint.getArgs());    //obtiene los argumentos de los métodos y los convierte a String para poder mostrarlos
        logger.info("Antes: "+ method + " con los argumentos "+args);
    }

    //@After("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))") //acá se define cuándo se intercepta y qué clases //"*" cualquier tipo de retorno //"(..)" todos los argumentos //GreetingService.* todas las clases o GreetingService.sayHello a una clase específica y lo mismo se hace un nivel anterior para que aplique a todos los packages
    @After("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfter(JoinPoint jointPoint){

        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());    //obtiene los argumentos de los métodos y los convierte a String para poder mostrarlos
        logger.info("Después: "+ method + " con los argumentos "+args);
    }

    //@AfterReturning("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))") //acá se define cuándo se intercepta y qué clases //"*" cualquier tipo de retorno //"(..)" todos los argumentos //GreetingService.* todas las clases o GreetingService.sayHello a una clase específica y lo mismo se hace un nivel anterior para que aplique a todos los packages
    @AfterReturning("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfterReturning(JoinPoint jointPoint){

        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());    //obtiene los argumentos de los métodos y los convierte a String para poder mostrarlos
        logger.info("Después de retornar: "+ method + " con los argumentos "+args);
    }

    //@AfterThrowing("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))") //acá se define cuándo se intercepta y qué clases //"*" cualquier tipo de retorno //"(..)" todos los argumentos //GreetingService.* todas las clases o GreetingService.sayHello a una clase específica y lo mismo se hace un nivel anterior para que aplique a todos los packages
    @AfterThrowing("GreetingServicePointcuts.greetingLoggerPointCut()")
    public void loggerAfterThrowing(JoinPoint jointPoint){

        String method = jointPoint.getSignature().getName();
        String args = Arrays.toString(jointPoint.getArgs());    //obtiene los argumentos de los métodos y los convierte a String para poder mostrarlos
        logger.info("Después lanzar la excepción: "+ method + " con los argumentos "+args);
    }

    //@Around("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    @Around("GreetingServicePointcuts.greetingLoggerPointCut()")
    public Object loggerAround(ProceedingJoinPoint joinPoint) throws Throwable{

        logger.info("============================loggerAround============================");

        String method = joinPoint.getSignature().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        Object result = null;

        try{
            logger.info("El método "+method+ "() con los parámetros: "+args);
            result = joinPoint.proceed();
            logger.info("El método "+method+"() retorna el resultado: "+result);
            return result;
        }catch(Throwable e){
            logger.error("Error en la llamada del método: "+method+"()");
            throw e;
        }
    }

    //@Around("execution(* com.william.curso.springboot.app.aop.springbootaop.services.GreetingService.*(..))")
    @Around("GreetingServicePointcuts.greetingLoggerPointCut()")
    public Object loggerAroundWwun(ProceedingJoinPoint joinPoint) throws Throwable{
        
        logger.info("============================loggerAroundWwun============================");

        String method = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        args[0] = "Arg Modif";

        Object result = null;

        try{
            logger.info("El método "+method+ "() con los parámetros: "+Arrays.toString(args));
            result = joinPoint.proceed(args);
            logger.info("El método "+method+"() retorna el resultado: "+result);
            return result;
        }catch(Throwable e){
            logger.error("Error en la llamada del método: "+method+"()");
            throw e;
        }
    }
}
