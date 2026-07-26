package com.eazybytes.accounts.dto;

import com.eazybytes.accounts.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer information"
)
public record CustomerDto(
        @Schema(
                description = "Name of the customer",
                example = "John Doe"
        ) @NotBlank @Size(min = 5, max = 30) String name,
        @Schema(
                description = "Email of the customer",
                example = "john.doe@gmail.com"
        ) @NotBlank @Email String email,
        @Schema(
                description = "Mobile number of the customer",
                example = "491903725"
        ) @NotBlank @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must be 10 digits") String mobileNumber,
        AccountDto accountDto
) {

    public Customer toEntity() {
        return Customer.builder()
                .name(this.name)
                .email(this.email)
                .mobileNumber(this.mobileNumber)
                .build();
    }
}
