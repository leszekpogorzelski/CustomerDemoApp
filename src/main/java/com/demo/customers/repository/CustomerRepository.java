package com.demo.customers.repository;

import com.demo.customers.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(final Long id);

    @Override
    Customer save(Customer customer);

    @Override
    long count();
}
