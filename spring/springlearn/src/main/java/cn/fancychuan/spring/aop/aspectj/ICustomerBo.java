package cn.fancychuan.spring.aop.aspectj;

public interface ICustomerBo {
    void addCustomer();
    void deleteCustomer();
    String addCustomerReturnValue();
    void addCustomerThrowException() throws Exception;
    void addCustomerAround(String name);
}
