package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Customer Detail",
        description = "Schema to hold Customer Details information"
)
public record CustomerDetailDto(
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
        @Schema(
                description = "Account type of the account",
                example = "SAVING | EXPENSE"
        )
        String accountType,
        @Schema(
                description = "Branch address of the account",
                example = "123 Death Star"
        )
        String branchAddress
) {
}