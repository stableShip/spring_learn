package com.example.demo.controller;

import com.example.demo.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证advice下统一错误处理
 */
@RestController
@EnableAutoConfiguration
@Slf4j
public class ExceptionController {

    /**
     * 系统错误全局捕获测试
     * @param request
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/exception")
    private String systemExceptionTest(HttpServletRequest request) throws Exception {
        throw new Exception("系统错误");
    }

    /**
     * 自定义错误全局捕获测试
     * @return
     * @throws Exception
     */
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "/baseException")
    private String baseExceptionTest() throws Exception {
        throw new BaseException("1000", "测试自定义错误");
    }
}
