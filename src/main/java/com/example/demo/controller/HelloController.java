package com.example.demo.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    private String Hello() {
        System.out.println("Hello World!");
        return "Hello World!";
    }
}
