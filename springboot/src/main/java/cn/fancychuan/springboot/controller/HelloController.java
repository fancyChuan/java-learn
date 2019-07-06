package cn.fancychuan.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody // 这个函数的返回需要用这个注解
    @RequestMapping("/hello") // 这个注解用于映射请求地址
    public String hello() {
        return "hello spring boot";
    }
}
