package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Customer;

public record CustomerDto(String name, String email, String mobileNumber) {

    public Customer toEntity() {
        return Customer.builder()
                .name(this.name)
                .email(this.email)
                .mobileNumber(this.mobileNumber)
                .build();
    }
}
