package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CustomerDto(@NotBlank String name,
                          @NotBlank @Email String email,
                          @NotBlank String mobileNumber,
                          AccountDto accountDto) {

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
