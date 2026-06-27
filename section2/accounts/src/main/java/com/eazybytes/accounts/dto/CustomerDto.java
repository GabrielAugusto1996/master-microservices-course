package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Customer;

import java.time.LocalDateTime;

public record CustomerDto(String name, String email, String mobileNumber) {

    public Customer toEntity() {
        return Customer.builder()
                .name(this.name)
                .email(this.email)
                .mobileNumber(this.mobileNumber)
                .build();
    }

    public Customer toNewEntity() {
        Customer customer = Customer.builder()
                .name(this.name)
                .email(this.email)
                .mobileNumber(this.mobileNumber)
                .build();

        customer.setCreatedBy("account-microservice");
        customer.setCreatedAt(LocalDateTime.now());

        return customer;
    }
}
