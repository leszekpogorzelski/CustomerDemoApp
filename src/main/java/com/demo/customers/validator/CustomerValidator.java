package com.demo.customers.validator;

import com.demo.customers.domain.Customer;
import com.demo.customers.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerValidator {


    @Autowired
    DbService service;

    public boolean validateCustomer(Customer customer) {
        boolean validation = false;
        String idNumber = customer.getPersonalIdNumber();
        List<Customer> dbCustomersList = service.getAllCustomers();
        if(dbCustomersList.size() == 0) {
            validation = true;
        } else {
            for (Customer dbCustomer : dbCustomersList) {
                if (dbCustomer.getPersonalIdNumber().equals(idNumber)) {
                    validation = false;
                } else {
                    validation = true;
                }
            }
        }
     return validation;
    }
}
