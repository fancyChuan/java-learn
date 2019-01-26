package com.fancychuan.springmvc.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class User2 {
    @NotBlank(message = "name不能为空")
    private String name;
    @NotBlank(message = "密码无能为空")
    @Length(min = 3, max = 6, message = "密码只能3-6位")
    private String password;
    @Range(min = 18, max = 30, message = "年龄只能在18-30")
    private Integer age;
    @Pattern(regexp = "^1[0-9]{10}$", message = "需要正确格式的手机号")
    private String phone;
    @Email(message = "非法邮箱")
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
