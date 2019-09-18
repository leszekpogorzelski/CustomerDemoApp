package com.demo.customers.controller;

import com.demo.customers.domain.Customer;
import com.demo.customers.domain.CustomerDto;
import com.demo.customers.mapper.CustomerMapper;
import com.demo.customers.service.DbService;
import com.demo.customers.validator.CustomerValidator;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerValidator validator;

    @MockBean
    private DbService service;

    @MockBean
    private CustomerMapper mapper;

    @Test
    public void testGetCustomers() throws Exception {
        //Given
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "11111111111", "Jan", "Nowak", "jan@nowak.pl", "Kwiatowa", "15", "02-215", "Wroclaw"));
        customers.add(new Customer(2L, "11111111112", "Jan", "Nowak", "jan@nowak.pl", "Kwiatowa", "15", "02-215", "Wroclaw"));
        when(service.getAllCustomers()).thenReturn(customers);
        when(mapper.mapToCustomerDtoList(anyList())).thenCallRealMethod();
        //When & Then
        mockMvc.perform(get("/v1/customers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(new Integer(1))))
                .andExpect(jsonPath("$[0].firstName", is("Jan")));
    }

    @Test
    public void testGetCustomer() throws Exception {
        //Given
        Customer customer = new Customer(1L, "11111111111", "Jan", "Nowak", "jan@nowak.pl", "Kwiatowa", "15", "02-215", "Wroclaw");
        when(service.getCustomer(anyLong())).thenReturn(Optional.of(customer));
        when(mapper.mapToCustomerDto(ArgumentMatchers.any(Customer.class))).thenCallRealMethod();
        //When & Then
        mockMvc.perform(get("/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(new Integer(1))))
                .andExpect(jsonPath("firstName", is("Jan")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        // Given & When & Then
        mockMvc.perform(delete("/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //Given
        CustomerDto customerDto = new CustomerDto(1L, "11111111111", "Jan", "Nowak", "jan@nowak.pl", "Kwiatowa", "15", "02-215", "Wroclaw");
        Gson gson = new Gson();
        String jsonString = gson.toJson(customerDto);

        when(mapper.mapToCustomerDto(service.saveCustomer(mapper.mapToCustomer(customerDto)))).thenReturn(customerDto);

        mockMvc.perform(put("/v1/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(new Integer(1))))
                .andExpect(jsonPath("firstName", is("Jan")))
                .andExpect(jsonPath("lastName", is("Nowak")));
    }
}
