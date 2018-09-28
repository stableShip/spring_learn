package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class HelloController {

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET})
    private String Hello(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", "JIE");
        log.info("Hello World!");
        return "Hello World!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "getName")
    private String getSessionName(HttpServletRequest request){
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        return name;
    }
}
