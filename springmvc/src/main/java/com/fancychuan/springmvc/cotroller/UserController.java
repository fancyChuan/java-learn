package com.fancychuan.springmvc.cotroller;


import com.fancychuan.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 基于注解的控制器，可以同时处理多个请求动作
 */
@Controller
public class UserController {

    /**
     *  RequestMapping 用来映射一个请求和请求的方法，value="/register" 表示这个/register请求由Register方法处理
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value="/register")
    public String Register(User user, Model model) { // user是视图层传给控制层的表单数据, model是控制层返回给视图层的对象
        model.addAttribute("user", user);
        return "success";
    }
}
