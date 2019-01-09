package com.fancychuan.spring.spring_auto.services;

import com.fancychuan.spring.spring_auto.dao.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public String toString() {
        return "CustomerService [customerDAO=" + customerDao + "]";
    }
}
