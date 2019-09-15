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
    //@NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private long id;

    //@NotNull
    @Column(name = "PERSONALID", unique = true)
    private String personalIdNumber;

    //@NotNull
    @Column(name = "FIRSTNAME")
    private String firstName;

    //@NotNull
    @Column(name = "LASTNAME")
    private String lastName;

    //@NotNull
    //@Email
    @Column(name = "EMAIL")
    private String email;

    //@NotNull
    @Column(name = "STREET")
    private String street;

    //@NotNull
    @Column(name = "HOMENUMBER")
    private String homeNumber;

    //@NotNull
    @Column(name = "POSTALCODE")
    private String postal;

    //@NotNull
    @Column(name = "CITY")
    private String city;



}
