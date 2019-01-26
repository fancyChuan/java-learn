package com.fancychuan.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    /**
     *  表单有两项内容，这里的函数可以直接用username, password作为参数
     * @param session
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpSession session, String username, String password,
                        Model model) {
        // 判断用户名和密码是否正确
        if (username.equals("fancyChuan") && password.equals("123456")) {
            session.setAttribute("username", username);
            // 重定向到 test 请求
            return "redirect:test";
        } else {
            model.addAttribute("message", "wrong user name or password!");
            return "loginform";
        }
    }

    // 登出
    @RequestMapping(value = "/loginout")
    public String loginout(HttpSession session) throws Exception {
        session.invalidate();
        return "loginform";
    }
}
