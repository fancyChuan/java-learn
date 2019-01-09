package com.fancychuan.spring.spring_auto.services;

import com.fancychuan.spring.spring_auto.dao.CustomerDao;

public class CustomerService {
    CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public String toString() {
        return "CustomerService [customerDAO=" + customerDao + "]";
    }
}
