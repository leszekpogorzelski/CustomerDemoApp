package com.demo.customers.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "CUSTOMERS")
public class Customer {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true)
    private long id;

    @NotNull
    @Column(name = "PERSONAL_ID", unique = true)
    private String personalIdNumber;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "STREET")
    private String street;

    @NotNull
    @Column(name = "HOME_NUMBER")
    private String homeNumber;

    @NotNull
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @NotNull
    @Column(name = "CITY")
    private String city;



}
