package com.william.curso.springboot.app.aop.springbootaop.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.william.curso.springboot.app.aop.springbootaop.services.GreetingService;

@RestController
public class GreetingController {


    //no olvidar agregar la dependencia de aop en pom
    
    @Autowired
    private GreetingService greetingService;
    @GetMapping("/greeting")
    public ResponseEntity<?> greeting(){
        return ResponseEntity.ok(Collections.singletonMap("greeting", greetingService.sayHello("Pepe", "Hola que tal!")));
    }
}
