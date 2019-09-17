package com.demo.customers.controller;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(final String message) {
        super(message);
    }
}
