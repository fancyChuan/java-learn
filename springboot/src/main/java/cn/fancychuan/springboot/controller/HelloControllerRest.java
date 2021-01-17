package cn.fancychuan.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // 相当于@ResponseBody ＋ @Controller合在一起的作用
public class HelloControllerRest {

    @RequestMapping("/hello-rest") // 这个注解用于映射请求地址
    public String hello() {
        return "hello spring boot RestController";
    }
}
