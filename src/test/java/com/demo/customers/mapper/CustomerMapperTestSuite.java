package com.demo.customers.mapper;

import com.demo.customers.domain.Customer;
import com.demo.customers.domain.CustomerDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerMapperTestSuite {

    @Autowired
    CustomerMapper mapper;

    @Test
    public void testMapToCustomerDtoList() {

        //Given
        Customer customer = new Customer(1L, "11111111111", "Jan", "Nowak", "jan@nowak.pl", "Kwiatowa", "15", "02-215", "Wroclaw");
        Customer customer1 = new Customer(2L, "11111111112", "Jan", "Nowak", "jan@nowak.pl", "Kwiatowa", "15", "02-215", "Wroclaw");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer1);
        //When
        List<CustomerDto> taskDtoList = mapper.mapToCustomerDtoList(customerList);

        //Then
        Assert.assertEquals(2, taskDtoList.size());
    }

    @Test
    public void testMapToCustomerDto() {

        //Given
        Customer customer = new Customer(1L, "11111111111", "Jan", "Nowak", "jan@nowak.pl", "Kwiatowa", "15", "02-215", "Wroclaw");

        //When
        CustomerDto customerDto = mapper.mapToCustomerDto(customer);

        //Then
        Assert.assertEquals(1L, customerDto.getId());
    }

    @Test
    public void testMapToCustomer() {

        //Given
        CustomerDto customerDto = new CustomerDto(1L, "11111111111", "Jan", "Nowak", "jan@nowak.pl", "Kwiatowa", "15", "02-215", "Wroclaw");

        //When
        Customer customer = mapper.mapToCustomer(customerDto);

        //Then
        Assert.assertEquals(1L, customer.getId());
    }
}
