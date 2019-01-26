package com.fancychuan.springmvc.controller;


import com.fancychuan.springmvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 基于注解的控制器，可以同时处理多个请求动作 (TODO：可以同时处理是什么意思？)
 *
 * Controller修饰的类负责处理由DispatcherServlet分发过来的请求，不使用注解的话，需要在springmvc的配置文件中增加一个bean对象
 * 如： <bean class="com.fancychuan.springmvc.controller.UserController"></bean>
 */
@Controller
public class UserController {

    /**
     * RequestMapping 用来映射一个请求和请求的方法，常用属性有：
     *  1. value="/register" 表示这个localhost:8080/register请求由Register方法处理
     *  2. method 指定请求的方式
     *  3. consums 指定要处理的请求的Content-Type类型，例如 application/json， text/html
     *  4. produces 指定返回的内容类型，只有request请求头中的accept中包含该指定类型才返回
     *  5. params 指定request中必须包含某些参数值，才让该方法执行
     *  6. headers 指定请求头中必须包含某些指定的header值，才让方法处理。比如 headers="Referer=http://www.shiyanlou.com"
     *
     *  2~6这几点都比django做得好，django需要自己手动在views.py逻辑中判断
     *
     *  另外，RequestMapping也可以修饰类，比如：
     *      @RequestMapping(value="/user")
     *      public class UserController {}
     *      这个时候就表示 localhost:8080/user 开始的请求都会分发到这个类，类似于django的一个app。
     *      比如 localhost:8080/user/register 就会交给 UserController下面的Register() 方法去处理
     */
    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String Register(User user, Model model) { // user是视图层传给控制层的表单数据, model是控制层返回给视图层的对象，类似于django传到模板文件中处理的对象
        model.addAttribute("user", user);
        return "success";
    }
}
