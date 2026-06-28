package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CustomerDto(@NotBlank @Size(min = 5, max = 30) String name,
                          @NotBlank @Email String email,
                          @NotBlank  @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must be 10 digits") String mobileNumber,
                          AccountDto accountDto) {

    public Customer toEntity() {
        return Customer.builder()
                .name(this.name)
                .email(this.email)
                .mobileNumber(this.mobileNumber)
                .build();
    }
}
