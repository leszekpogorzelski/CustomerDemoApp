package com.demo.customers.controller;


import com.demo.customers.domain.CustomerDto;
import com.demo.customers.mapper.CustomerMapper;
import com.demo.customers.service.DbService;
import com.demo.customers.validator.CustomerValidator;
import com.demo.customers.validator.CustomerAlreadyExists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    CustomerValidator validator;

    @Autowired
    DbService service;

    @Autowired
    CustomerMapper customerMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public List<CustomerDto> getCustomers() {
        return customerMapper.mapToCustomerDtoList(service.getAllCustomers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) throws CustomerNotFoundException {
        return customerMapper.mapToCustomerDto(service.getCustomer(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found")));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers", consumes = APPLICATION_JSON_VALUE)
    public void createCustomer(@RequestBody CustomerDto customerDto) throws CustomerAlreadyExists, WrongDataInput {
        if (validator.validateCustomer(customerMapper.mapToCustomer(customerDto))) {
           try {
               service.saveCustomer(customerMapper.mapToCustomer(customerDto));
           } catch (Exception e) {
               throw new WrongDataInput("Wrong data format!\n" +
               "Correct example format:\n" +
                       "{\n" +
                       "  \"city\": \"Warszawa || Kedzierzyn-Kozle\",\n" +
                       "  \"email\": \"jan@kowalski.pl\",\n" +
                       "  \"firstName\": \"Jan\",\n" +
                       "  \"homeNumber\": \"15 || 15A || 15A/1a\",\n" +
                       "  \"lastName\": \"Kowalski\",\n" +
                       "  \"personalIdNumber\": \"12345678901\",\n" +
                       "  \"postalCode\": \"03-303\",\n" +
                       "  \"street\": \"Kwiatowa || Kubusia Puchatka\"\n" +
                       "}");
           }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customers")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) throws WrongDataInput {
        try {customerMapper.mapToCustomerDto(service.saveCustomer(customerMapper.mapToCustomer(customerDto)));
        } catch (Exception e) {
            throw new WrongDataInput("Couldn't update data! Wrong data format!\n" +
                    "Correct example format:\n" +
                    "{\n" +
                    "  \"city\": \"Warszawa || Kedzierzyn-Kozle\",\n" +
                    "  \"email\": \"jan@kowalski.pl\",\n" +
                    "  \"firstName\": \"Jan\",\n" +
                    "  \"homeNumber\": \"15 || 15A || 15A/1a\",\n" +
                    "  \"lastName\": \"Kowalski\",\n" +
                    "  \"personalIdNumber\": \"12345678901\",\n" +
                    "  \"postalCode\": \"03-303\",\n" +
                    "  \"street\": \"Kwiatowa || Kubusia Puchatka\"\n" +
                    "}");
        }
        return customerDto;
        }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) throws CustomerNotFoundException {
        try {
            service.deleteCustomer(customerId);
        } catch (Exception e) {
            throw new CustomerNotFoundException("Customer not found in DataBase!");
        }
    }

}


