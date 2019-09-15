package com.demo.customers.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomerDto {

    private long id;
    private String personalIdNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String homeNumber;
    private String postal;
    private String city;
}
