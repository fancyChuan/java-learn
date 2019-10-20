package com.fancychuan.spring.aop.aspectj;

public class CustomerBo implements ICustomerBo {
    @Override
    public void addCustomer() {
        System.out.println("正在跑 addCustomer()");
    }

    @Override
    public void deleteCustomer() {
        System.out.println("正在跑 deleteCustomer()");
    }

    @Override
    public String addCustomerReturnValue() {
        System.out.println("正在跑 addCustomerReturnValue()");
        return "abc";
    }

    @Override
    public void addCustomerThrowException() throws Exception {
        System.out.println("正在跑 addCustomerThrowException()");
        throw new Exception("一般错误啦");
    }

    @Override
    public void addCustomerAround(String name) {
        System.out.println("正在跑 addCustomerAround(), 参数为：" + name);
    }
}
