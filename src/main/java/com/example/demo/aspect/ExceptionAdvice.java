package com.example.demo.aspect;

import com.example.demo.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ExceptionAdvice {

    /**
     * 全局异常捕捉处理 code默认100
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map errorHandler(Exception ex) {
        ex.printStackTrace();
        Map map = new HashMap();
        map.put("code", 100);
        map.put("msg", "系统错误");
        return map;
    }

    /**
     * 拦截捕捉自定义异常 BaseException.class
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public Map myErrorHandler(BaseException ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMsg());
        return map;
    }
}
