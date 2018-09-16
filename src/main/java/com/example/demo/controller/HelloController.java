package com.example.demo.controller;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@Slf4j
public class HelloController {

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    private String Hello() {
        log.info("Hello World!");
        return "Hello World!";
    }
}
