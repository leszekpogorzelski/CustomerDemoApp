package com.demo.customers.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity(name = "CUSTOMERS")
public class Customer {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private long id;

    @NotNull
    @Pattern(regexp="\\d{11}")
    @Column(name = "PERSONAL_ID", unique = true)
    private String personalIdNumber;

    @NotNull
    @Pattern(regexp ="[A-Z][a-z]+")
    @Size(min = 3, max = 20)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull
    @Pattern(regexp ="[A-Z][a-z]+")
    @Size(min = 3, max = 30)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Pattern(regexp = ".+@.+\\..[a-z]+")
    @Email
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Pattern(regexp ="([A-Z][a-z]+)|([A-Z][a-z]+\\ [A-Z][a-z]+)")
    @Column(name = "STREET")
    private String street;

    @NotNull
    @Pattern(regexp ="([0-9]+)|([0-9]+[A-Z])|([0-9]+\\/[0-9]+)|([0-9]+[A-Z]\\/[0-9]+[a-z])" +
            "|([0-9]+\\/[0-9]+[a-z])|([0-9]+[A-Z]\\/[0-9]+)")
    @Column(name = "HOME_NUMBER")
    private String homeNumber;

    @NotNull
    @Pattern(regexp ="\\d{2}\\-\\d{3}")
    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @NotNull
    @Pattern(regexp ="([A-Z][a-z]+)|([A-Z][a-z]+\\-[A-Z][a-z]+)")
    @Column(name = "CITY")
    private String city;
}
