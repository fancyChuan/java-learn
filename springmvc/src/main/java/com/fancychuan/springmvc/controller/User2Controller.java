package com.fancychuan.springmvc.controller;

import com.fancychuan.springmvc.entity.User2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class User2Controller {

    @RequestMapping(value="/registerform")
    public String registerform(Model model) {
        model.addAttribute(new User2());
        return "register2";
    }

    @RequestMapping(value="/register2")
    public String register(@Valid @ModelAttribute("user2") User2 user2, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "register2";
        }
        model.addAttribute(user2);
        return "success2";
    }
}
