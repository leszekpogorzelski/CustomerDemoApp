package com.demo.customers.mapper;

import com.demo.customers.domain.Customer;
import com.demo.customers.domain.CustomerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {


    public Customer mapToCustomer(final CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getPersonalIdNumber(),
                customerDto.getFirstName(),
                customerDto.getLastName(),
                customerDto.getEmail(),
                customerDto.getStreet(),
                customerDto.getHomeNumber(),
                customerDto.getPostalCode(),
                customerDto.getCity());
    }

    public CustomerDto mapToCustomerDto(final Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getPersonalIdNumber(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getStreet(),
                customer.getHomeNumber(),
                customer.getPostalCode(),
                customer.getCity());
    }

    public List<CustomerDto> mapToCustomerDtoList(final List<Customer> customerList) {
        return customerList.stream()
                .map(t -> new CustomerDto(t.getId(), t.getPersonalIdNumber(), t.getFirstName(),
                        t.getLastName(), t.getEmail(), t.getStreet(), t.getHomeNumber(),
                        t.getPostalCode(), t.getCity()))
                .collect(Collectors.toList());
    }
}
