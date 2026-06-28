package com.eazybytes.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CustomerDetailDto(@NotBlank String name,
                                @NotBlank @Email String email,
                                @NotBlank String mobileNumber,
                                @NotBlank String accountType,
                                @NotBlank String branchAddress) {
}