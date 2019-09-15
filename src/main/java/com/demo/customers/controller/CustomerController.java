package com.demo.customers.controller;

import com.demo.customers.domain.CustomerDto;
import com.demo.customers.mapper.CustomerMapper;
import com.demo.customers.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class CustomerController {

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
        return customerMapper.mapToCustomerDto(service.getCustomer(customerId).orElseThrow(CustomerNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers", consumes = APPLICATION_JSON_VALUE)
    public void createCustomer(@RequestBody CustomerDto customerDto) {
        service.saveCustomer(customerMapper.mapToCustomer(customerDto));

    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customers")
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
        return customerMapper.mapToCustomerDto(service.saveCustomer(customerMapper.mapToCustomer(customerDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cus/{taskId}")
    public void deleteCustomer(@PathVariable Long taskId) {
        service.deleteCustomer(taskId);
    }

}
