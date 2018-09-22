package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
@EnableAutoConfiguration
@Slf4j
public class RestTemplateController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/requestLocalHello")
    private String requestLocalHello(HttpServletRequest request) {
        String data = restTemplate.getForObject("http://localhost:8080", String.class);
        log.info(data);
        return data;
    }

    @GetMapping(value = "/requestBaidu")
    private String requestBaidu(HttpServletRequest request) {
        String data = restTemplate.getForObject("http://baidu.com", String.class);
        log.info(data);
        return data;
    }

    @GetMapping(value = "/requestLocalException")
    private String requestLocalException(HttpServletRequest request) {
        HashMap<String, Object> postData = new HashMap<>();
        String data = restTemplate.postForObject("http://localhost:8080/baseException", postData, String.class);
        log.info(data);

        return data;
    }


}
