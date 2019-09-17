package com.demo.customers.validator;

public class CustomerAlreadyExists extends Exception {
    public CustomerAlreadyExists(final String message) {
        super(message);
    }
}
